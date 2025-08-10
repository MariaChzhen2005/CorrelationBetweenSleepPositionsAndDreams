function setActiveNav() {
  const path = location.pathname.split('/').pop() || 'index.html';
  document.querySelectorAll('nav a[data-nav]').forEach(a => {
    const target = a.getAttribute('data-nav');
    const isIndex = (path === '' || path === 'index.html');
    const active = (target === 'home' && isIndex) || path.startsWith(target);
    if (active) a.classList.add('active');
  });
}

function initTheme() {
  const key = 'theme';
  const stored = localStorage.getItem(key);
  const prefersDark = matchMedia('(prefers-color-scheme: dark)').matches;
  const theme = stored || (prefersDark ? 'dark' : 'dark'); // dark-first
  document.documentElement.dataset.theme = theme;
  const toggle = document.querySelector('#theme-toggle');
  if (toggle) {
    toggle.addEventListener('click', () => {
      const next = document.documentElement.dataset.theme === 'dark' ? 'light' : 'dark';
      document.documentElement.dataset.theme = next;
      localStorage.setItem(key, next);
    });
  }
}

window.addEventListener('DOMContentLoaded', () => {
  setActiveNav();
  initTheme();
}); 