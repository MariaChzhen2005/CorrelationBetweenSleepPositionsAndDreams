function setActiveNav() {
  const path = location.pathname.split('/').pop() || 'index.html';
  document.querySelectorAll('nav a[data-nav]').forEach(a => {
    const target = a.getAttribute('data-nav');
    const isIndex = (path === '' || path === 'index.html');
    const active = (target === 'home' && isIndex) || path.startsWith(target);
    if (active) a.classList.add('active');
  });
}

window.addEventListener('DOMContentLoaded', () => {
  setActiveNav();
}); 