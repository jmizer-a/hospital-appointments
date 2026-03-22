const BASE = '/api/doctors';

export const getDoctors = () =>
  fetch(BASE).then(res => res.json());

export const getDoctor = (id) =>
  fetch(`${BASE}/${id}`).then(res => res.json());

export const saveDoctor = (doctor) => {
  const method = doctor.id ? 'PUT' : 'POST';
  const url = doctor.id ? `${BASE}/${doctor.id}` : BASE;
  return fetch(url, {
    method,
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(doctor),
  }).then(res => res.json());
};

export const deleteDoctor = (id) =>
  fetch(`${BASE}/${id}`, { method: 'DELETE' });
