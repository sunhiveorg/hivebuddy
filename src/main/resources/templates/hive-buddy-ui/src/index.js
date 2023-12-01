import React from 'react';
import * as ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import '@fontsource/roboto/300.css';
import '@fontsource/roboto/400.css';
import '@fontsource/roboto/500.css';
import '@fontsource/roboto/700.css';
import {
    createBrowserRouter,
    RouterProvider,
} from "react-router-dom";
import Team from './pages/Team/Team';
import HiveBuddy from './pages/HiveBuddy/HiveBuddy';

const router = createBrowserRouter([
    {
        path: "/",
        element: <App/>,
    },
    {
        path: "/our-team",
        element: <Team />
    },
    {
        path: "/what-is-hiveBuddy",
        element: <HiveBuddy />
    },
]);

const root = ReactDOM.createRoot(document.getElementById('root')).render(
    <React.StrictMode>
        <RouterProvider router={router} />
    </React.StrictMode>
);
