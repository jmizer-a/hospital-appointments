import { useState, useEffect, useRef } from 'react';
import { getDoctors, getDoctor, saveDoctor, deleteDoctor } from './api/doctorApi';
import { getAppointments, deleteAppointment, saveAppointment} from './api/appointmentApi';
import { login as loginApi } from './api/authApi';
import './App.css';

function App() {
  const emptyDoctorForm = { firstName: '', lastName: '' };
  const emptyAppointmentForm = { datetime: '', note: '', appointmentType: '' };
  const emptyLoginForm = { login: '', password: '' };

  const [backendMessage, setBackendMessage] = useState('Loading...');
  const [doctors, setDoctors] = useState([]);
  const [appointments, setAppointments] = useState([]);
  
  const [doctorForm, setDoctorForm] = useState(emptyDoctorForm);
  const [appointmentForm, setAppointmentForm] = useState(emptyAppointmentForm);
  const [loginForm, setLoginForm] = useState(emptyLoginForm);

  const [loggedPatientId, setLoggedPatientId] = useState(null);
  const [contextMenu, setContextMenu] = useState(null);
  const [showAppointmentModal, setShowAppointmentModal] = useState(false);
  const [selectedDoctor, setSelectedDoctor] = useState(null);
  

  useEffect(() => {
    fetch('/api/hello')
      .then(res => res.json())
      .then(data => setBackendMessage(data.message))
      .catch(() => setBackendMessage('Error connecting to backend'));

    loadDoctors();
  }, []);

  const loadAppointments = () => {
    getAppointments()
      .then(setAppointments)
      .catch(err => console.error('Failed to load appointments', err));
  };

  const loadDoctors = () => {
    getDoctors()
      .then(setDoctors)
      .catch(err => console.error('Failed to load doctors', err));
  };

  const handleLoginChange = e => setLoginForm({...loginForm, [e.target.name]: e.target.value });

  const handleLoginSubmit = async (e) => {
    e.preventDefault();
    try {
      const data = await loginApi(loginForm);
      setLoggedPatientId(data.patientId);
      setLoginForm(emptyLoginForm);
      loadAppointments();
    } catch (err) {
      alert('Login failed: ' + err.message);
    }
  };

  const handleLogout = () => {
    setLoggedPatientId(null);
    setSelectedDoctor(null);
    loadAppointments();
    setAppointmentForm(emptyAppointmentForm);
  };

  const handleDoctorChange = e => setDoctorForm({...doctorForm, [e.target.name]: e.target.value });

  const handleDoctorSubmit = async (e) => {
    e.preventDefault();
    try {
      await saveDoctor(doctorForm);
      setDoctorForm(emptyDoctorForm);
      loadDoctors();
    } catch (err) {
      alert('Failed to save doctor');
    }
  };

  const handleDoctorDelete = async (id) => {
    try {
      await deleteDoctor(id);
      setDoctors(doctors.filter(d => d.id !== id));
    } catch (err) {
      alert('Failed to delete doctor');
    }
  };

  const handleDoctorContextMenu = (e, doctor) => {
    e.preventDefault();
    setContextMenu({ x: e.pageX, y: e.pageY, doctor });
  };

  const closeContextMenu = () => setContextMenu(null);

  const openAppointmentModal = () => {
    setShowAppointmentModal(true);
    closeContextMenu();
  };

  const closeAppointmentModal = () => {
    setShowAppointmentModal(false);
    setAppointmentForm(emptyAppointmentForm);
    setSelectedDoctor(null);
  };

  const handleAppointmentChange = e =>
    setAppointmentForm({...appointmentForm, [e.target.name]: e.target.value });

  const handleAppointmentSubmit = async (e) => {
    e.preventDefault();

    if (!loggedPatientId) {
      alert('Please login first');
      return;
    }

    if (!selectedDoctor) {
      alert('No doctor selected');
      return;
    }

    try {
      const payload = {
        patientId: loggedPatientId,
        doctorId: selectedDoctor.id,
        datetime: appointmentForm.datetime,
        note: appointmentForm.note,
        appointmentType: appointmentForm.appointmentType,
      };

      await saveAppointment(payload);

      alert('Appointment created!');
      loadAppointments();
      closeAppointmentModal();
    } catch (err) {
      alert('Error: ' + err.message);
    }
  };

  const onMakeAppointmentClick = () => {
    setSelectedDoctor(contextMenu.doctor);
    openAppointmentModal();
  };

  const handleAppointmentDelete = async (id) => {
    try {
      const res = await deleteAppointment(id);
      if (!res.ok) throw new Error('Failed to delete appointment');
      setAppointments(appointments.filter(app => app.id !== id));
    } catch (err) {
      alert('Error deleting appointment: ' + err.message);
    }
  };

  useEffect(() => {
    const handleClick = () => closeContextMenu();
    window.addEventListener('click', handleClick);
    return () => window.removeEventListener('click', handleClick);
  }, []);

  return (
    <div className="App" style={{ padding: '2rem', fontFamily: 'system-ui' }}>
      <header style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
        <h1>React + Spring Boot</h1>
      </header>

      <div className="login-panel">
        {loggedPatientId ? (
          <>
            <div>Logged in as Patient ID: {loggedPatientId}</div>
            <button onClick={handleLogout}>Logout</button>
          </>
        ) : (
          <form className='login-form' onSubmit={handleLoginSubmit}>
            <input
              name="login"
              placeholder="Login"
              value={loginForm.login}
              onChange={handleLoginChange}
              required
            />
            <input
              name="password"
              placeholder="Password"
              type="password"
              value={loginForm.password}
              onChange={handleLoginChange}
              required
            />
            <button type="submit">Login</button>
          </form>
        )}
      </div>

      <p>Backend says: {backendMessage}</p>

      <section style={{ marginTop: '2rem' }}>
        <h2>Add Doctor</h2>
        <form onSubmit={handleDoctorSubmit}>
          <input
            name="firstName"
            placeholder="First"
            value={doctorForm.firstName}
            onChange={handleDoctorChange}
            required
          />
          <input
            name="lastName"
            placeholder="Last"
            value={doctorForm.lastName}
            onChange={handleDoctorChange}
            required
          />
          <button type="submit">Save</button>
        </form>
      </section>

      <section style={{ marginTop: '2rem' }}>
        <h2>Appointments</h2>
        {appointments.length === 0 ? (
          <p>No appointments found.</p>
        ) : (
          <div className="appointments-container">
            {appointments.map(app => {
              const doc = doctors.find(d => d.id === app.doctorId);
              const docName = doc ? `Dr. ${doc.firstName} ${doc.lastName}` : 'Unknown doctor';
              return (
                <div
                  className="appointment"
                  key={app.id}
                  style={{
                    display: 'flex',
                    justifyContent: 'space-between',
                    alignItems: 'center',
                    borderBottom: '1px solid #ddd',
                    padding: '0.5rem 0',
                  }}
                >
                  <div>
                    <strong>{new Date(app.datetime).toLocaleString()}</strong> — {docName} — {app.appointmentType}
                    {app.note ? ` — Note: ${app.note}` : ''}
                  </div>
                  <button onClick={() => handleAppointmentDelete(app.id)}>🗑️</button>
                </div>
              );
            })}
          </div>
        )}
      </section>


      <section style={{ marginTop: '2rem' }}>
        <h2>Doctor List</h2>
        <div className="doctors-container">
          {doctors.map(d => (
            <div
              className="doctor"
              key={d.id}
              onContextMenu={e => handleDoctorContextMenu(e, d)}
            >
              <span>{d.firstName} {d.lastName}</span>
              <button onClick={() => handleDoctorDelete(d.id)}>🗑️</button>
            </div>
          ))}
        </div>
      </section>

      {/* Context menu */}
      {contextMenu && (
        <ul
          className="context-menu"
          style={{
            top: contextMenu.y,
            left: contextMenu.x,
            position: 'absolute',
            listStyle: 'none',
            margin: 0,
          }}
        >
          <li
            style={{ cursor: 'pointer' }}
            onClick={onMakeAppointmentClick}
          >
            Make Appointment
          </li>
        </ul>
      )}

      {/* Appointment Modal */}
      {showAppointmentModal && (
        <div
          className="modal-overlay"
          style={{
            position: 'fixed',
            top:0, left:0, right:0, bottom:0,
            backgroundColor: 'rgba(0,0,0,0.3)',
            display: 'flex',
            justifyContent: 'center',
            alignItems: 'center',
            zIndex: 2000,
          }}
        >
          <div
            className="appointment-modal"
          >
            <h3>Make Appointment</h3>
            <p><b>Doctor:</b> {selectedDoctor.firstName} {selectedDoctor.lastName}</p>
            <form onSubmit={handleAppointmentSubmit}>
              <label>
                Date and Time:
                <input
                  type="datetime-local"
                  name="datetime"
                  value={appointmentForm.datetime}
                  onChange={handleAppointmentChange}
                  required
                />
              </label>
              <br />
              <label>
                Appointment Type:
                <select
                  name="appointmentType"
                  value={appointmentForm.appointmentType}
                  onChange={handleAppointmentChange}
                  required
                >
                  <option value="">-- Select Type --</option>
                  <option value="EXAM">Examination</option>
                  <option value="CONS">Consultation</option>
                </select>
              </label>
              <br />
              <label>
                Note:
                <textarea
                  name="note"
                  value={appointmentForm.note}
                  onChange={handleAppointmentChange}
                  rows={3}
                />
              </label>
              <br />
              <button type="submit">Confirm</button>{' '}
              <button type="button" onClick={closeAppointmentModal}>Cancel</button>
            </form>
          </div>
        </div>
      )}
    </div>
  );
}

export default App;
