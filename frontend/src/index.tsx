import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';

/**
 * This is the entrance of your React app. It renders your application
 * at the HTML element with id="root". Most often, you do not want to
 * change this file.
 */
const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);
/**
 * Render your application, App is the component we define in App.tsx.
 */
root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);
