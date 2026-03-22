const BASE = '/api/appointments';

export const getAppointments = () =>
  fetch(BASE).then(res => res.json());

export const getAppointment = (id) =>
  fetch(`${BASE}/${id}`).then(res => res.json());

export const saveAppointment = (appointment) => {
  const method = appointment.id ? 'PUT' : 'POST';
  const url = appointment.id ? `${BASE}/${appointment.id}` : BASE;
  return fetch(url, {
    method,
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(appointment),
  }).then(res => res.json());
};

export const deleteAppointment = (id) =>
  fetch(`${BASE}/${id}`, { method: 'DELETE' });
