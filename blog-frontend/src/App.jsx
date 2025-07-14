import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import BlogHomePage from "./Pages/BlogHomePage.jsx";
import PostDetail from "./Pages/PostDetail.jsx"

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<BlogHomePage />} />
                <Route path="/posts/:id" element={<PostDetail />} />
            </Routes>
        </BrowserRouter>
    );
}

export default App;

