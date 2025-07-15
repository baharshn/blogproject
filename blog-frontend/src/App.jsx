import React, { lazy, Suspense } from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import BlogHomePage from "./Pages/BlogHomePage.jsx";

const PostDetail = lazy(() => import('./Pages/PostDetail.jsx'));

function App() {
    return (
        <BrowserRouter>
            <Suspense fallback={<div>Loading...</div>}>
                <Routes>
                    <Route path="/" element={<BlogHomePage />} />
                    <Route path="/posts/:id" element={<PostDetail />} />
                </Routes>
            </Suspense>
        </BrowserRouter>
    );
}

export default App;


/*
import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import BlogHomePage from "./Pages/BlogHomePage.jsx";



function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<BlogHomePage />} />
                <Route path="/posts/:id" element={<div>Detail Page</div>} />
            </Routes>
        </BrowserRouter>
    );
}

export default App;


import React from 'react';

import BlogHomePage from "./Pages/BlogHomePage.jsx";

function App() {
    return (
        <div>
            <BlogHomePage />
        </div>
    );
}

export default App;



import { BrowserRouter, Routes, Route } from 'react-router-dom';
import HomePage from './Pages/HomePage.jsx';
import PostDetail from './Pages/PostDetail.jsx';

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<HomePage />} />
                <Route path="/posts/:id" element={<PostDetail />} />
            </Routes>
        </BrowserRouter>
    );
}

export default App;
 */