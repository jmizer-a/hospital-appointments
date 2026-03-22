export const login = ({ login, password }) =>
  fetch('/api/auth/login', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ login, password })
  }).then(res => {
    if (!res.ok) throw new Error('Invalid login');
    return res.json();
  });
