/* global d3 */

const DATA_URL = 'data/SleepPositionsData.csv';

const POSITION_GROUPS = {
  back: new Set([1,4,5,12,13,14,16,20,22]),
  left: new Set([3,6,11,21]),
  right: new Set([9,18,19,23]),
  stomach: new Set([2,7,8,10,15,17,24])
};

const METRICS = ['vividity', 'scariness', 'happiness', 'bizarreness', 'romance'];

async function loadData() {
  const raw = await d3.csv(DATA_URL);
  const rows = raw.map(r => ({
    age: toNum(r.age),
    gender: (r.gender || '').trim(),
    sleepAvg: normalizeSleep(r.sleepAvg),
    sleepPos: toNum(r.sleepPos),
    vividity: toNum(r.vividity),
    wakePos: toNum(r.wakePos),
    earlyBirdNightOwl: (r.earlyBirdNightOwl || '').trim(),
    caffeine: (r.caffeine || '').trim(),
    screenTime: parseScreen(r.screenTime),
    scariness: toNum(r.scariness),
    happiness: toNum(r.happiness),
    bizarreness: toNum(r.bizarreness),
    romance: toNum(r.romance)
  }));
  return rows;
}

function toNum(v) {
  const n = +String(v || '').replace(/[^0-9.\-]/g, '');
  return Number.isFinite(n) ? n : null;
}

function normalizeSleep(v) {
  if (!v) return null;
  const str = String(v).toLowerCase();
  const m = str.match(/(\d+)(?:\s*[-–]\s*(\d+))?/);
  if (m) {
    const a = +m[1];
    const b = m[2] ? +m[2] : a;
    return (a + b) / 2;
  }
  if (/\b(10\+)\b/.test(str)) return 10;
  return toNum(v);
}

function parseScreen(v) {
  if (!v) return null;
  const str = String(v).toLowerCase();
  const m = str.match(/(\d+)(?:\s*[-–]\s*(\d+))?/);
  if (m) {
    const a = +m[1];
    const b = m[2] ? +m[2] : a;
    return (a + b) / 2;
  }
  if (/more than half the day|all day|10\+/.test(str)) return 10;
  return toNum(v);
}

function categorizePosition(pos) {
  if (pos == null) return null;
  for (const [cat, set] of Object.entries(POSITION_GROUPS)) {
    if (set.has(pos)) return cat;
  }
  return null;
}

function aggregateByPosition(rows) {
  const out = { back: {}, left: {}, right: {}, stomach: {} };
  const counts = { back: {}, left: {}, right: {}, stomach: {} };
  for (const cat of Object.keys(out)) {
    METRICS.forEach(m => { out[cat][m] = 0; counts[cat][m] = 0; });
  }
  for (const r of rows) {
    const cat = categorizePosition(r.wakePos);
    if (!cat) continue;
    for (const m of METRICS) {
      const val = r[m];
      if (val != null && val !== 0) {
        out[cat][m] += val;
        counts[cat][m] += 1;
      }
    }
  }
  for (const cat of Object.keys(out)) {
    for (const m of METRICS) {
      out[cat][m] = counts[cat][m] ? out[cat][m] / counts[cat][m] : null;
    }
  }
  return out;
}

function prepHeatmapData(agg) {
  const cats = ['back', 'left', 'right', 'stomach'];
  const data = [];
  for (const cat of cats) {
    for (const m of METRICS) {
      data.push({ cat, metric: m, value: agg[cat][m] });
    }
  }
  return data;
}

function renderHeatmap(el, data) {
  const width = el.clientWidth - 24;
  const height = el.clientHeight - 24;
  const margin = { top: 30, right: 10, bottom: 50, left: 80 };
  const w = width - margin.left - margin.right;
  const h = height - margin.top - margin.bottom;

  el.innerHTML = '';
  const svg = d3.select(el).append('svg').attr('width', width).attr('height', height);
  const g = svg.append('g').attr('transform', `translate(${margin.left},${margin.top})`);

  const cats = ['back', 'left', 'right', 'stomach'];
  const metrics = METRICS;
  const x = d3.scaleBand().domain(metrics).range([0, w]).padding(0.05);
  const y = d3.scaleBand().domain(cats).range([0, h]).padding(0.05);

  const values = data.map(d => d.value).filter(v => v != null);
  const color = d3.scaleSequential().domain(d3.extent(values)).interpolator(d3.interpolatePuBuGn);

  g.append('g').attr('class', 'x-axis').attr('transform', `translate(0,${h})`).call(d3.axisBottom(x));
  g.append('g').attr('class', 'y-axis').call(d3.axisLeft(y));

  g.selectAll('rect').data(data).enter().append('rect')
    .attr('x', d => x(d.metric))
    .attr('y', d => y(d.cat))
    .attr('width', x.bandwidth())
    .attr('height', y.bandwidth())
    .attr('rx', 6)
    .attr('fill', d => d.value == null ? 'rgba(255,255,255,0.05)' : color(d.value));

  const fmt = d3.format('.2f');
  g.selectAll('text.value').data(data).enter().append('text')
    .attr('class', 'value')
    .attr('x', d => x(d.metric) + x.bandwidth() / 2)
    .attr('y', d => y(d.cat) + y.bandwidth() / 2 + 4)
    .attr('text-anchor', 'middle')
    .attr('fill', '#0b0f1e')
    .attr('font-size', 12)
    .text(d => (d.value == null ? '' : fmt(d.value)));

  const legendW = 160, legendH = 10;
  const legend = svg.append('g').attr('transform', `translate(${width - legendW - 12}, ${margin.top})`);
  const lg = d3.range(legendW).map(i => i / (legendW - 1));
  const extent = d3.extent(values);
  legend.selectAll('rect').data(lg).enter().append('rect')
    .attr('x', (d, i) => i)
    .attr('y', 0)
    .attr('width', 1)
    .attr('height', legendH)
    .attr('fill', d => color(extent[0] + d * (extent[1] - extent[0])));
  legend.append('text').attr('x', 0).attr('y', -6).attr('fill', '#aab0c0').attr('font-size', 12).text('Avg score');
}

function renderBar(el, agg, metric) {
  const cats = ['back', 'left', 'right', 'stomach'];
  const data = cats.map(cat => ({ cat, value: agg[cat][metric] }));
  const width = el.clientWidth - 24;
  const height = el.clientHeight - 24;
  const margin = { top: 20, right: 10, bottom: 50, left: 60 };
  const w = width - margin.left - margin.right;
  const h = height - margin.top - margin.bottom;
  el.innerHTML = '';
  const svg = d3.select(el).append('svg').attr('width', width).attr('height', height);
  const g = svg.append('g').attr('transform', `translate(${margin.left},${margin.top})`);
  const x = d3.scaleBand().domain(cats).range([0, w]).padding(0.2);
  const y = d3.scaleLinear().domain([0, d3.max(data, d => d.value || 0) || 10]).nice().range([h, 0]);
  g.append('g').attr('transform', `translate(0,${h})`).call(d3.axisBottom(x));
  g.append('g').call(d3.axisLeft(y));
  const color = d3.scaleOrdinal().domain(cats).range(['#9aa5ff', '#a6e3e9', '#b8c0ff', '#8bd5ca']);
  g.selectAll('rect.bar').data(data).enter().append('rect')
    .attr('class', 'bar')
    .attr('x', d => x(d.cat))
    .attr('y', d => y(d.value || 0))
    .attr('width', x.bandwidth())
    .attr('height', d => h - y(d.value || 0))
    .attr('rx', 6)
    .attr('fill', d => color(d.cat));
}

async function initExplore() {
  const rows = await loadData();
  const agg = aggregateByPosition(rows);
  const heatEl = document.querySelector('#heatmap');
  if (heatEl) {
    const heatData = prepHeatmapData(agg);
    renderHeatmap(heatEl, heatData);
  }
  const barEl = document.querySelector('#bar');
  const select = document.querySelector('#metric-select');
  if (barEl && select) {
    const update = () => renderBar(barEl, agg, select.value);
    select.addEventListener('change', update);
    select.addEventListener('input', update);
    update();
  }
}

window.addEventListener('DOMContentLoaded', () => {
  const explore = document.body.dataset.page === 'explore';
  if (explore) initExplore();
}); 