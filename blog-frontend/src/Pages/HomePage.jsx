import React, { useState } from 'react';
import '../Styles/HomePage.css';

const Homepage = () => {
    const [showLogin, setShowLogin] = useState(false);

    return (
        <div className="homepage">
            <header className="homepage-header">
                <h1 className="homepage-title">Seasons in Colour</h1>
                <nav className="homepage-nav">
                    <a href="#">Home</a>
                    <a href="#">About</a>
                    <a href="#">Blog</a>
                    <a href="#">Contact</a>
                </nav>
                <button onClick={() => setShowLogin(true)} className="login-button">
                    Login
                </button>
            </header>

            <section className="homepage-hero">
                <div className="hero-overlay">
                    <h2 className="hero-title">Seasons in Colour</h2>
                    <p className="hero-subtitle">Get the latest trends from the Seasonsincolour interiors blog</p>
                </div>
            </section>

            <section className="homepage-posts">
                <h3 className="section-title">Latest Stories</h3>
                <div className="posts-grid">
                    <article className="post-card">
                        <img src="/api/placeholder/400/300" alt="Sample" className="post-image" />
                        <div className="post-content">
                            <h4 className="post-title">Cottage Aesthetic for cosy living</h4>
                            <p className="post-excerpt">
                                If you love the comfort of cosy nooks and calming colors found in nature...
                            </p>
                            <button className="read-more">Read More</button>
                        </div>
                    </article>
                    <article className="post-card">
                        <img src="/api/placeholder/400/300" alt="Sample" className="post-image" />
                        <div className="post-content">
                            <h4 className="post-title">Home Renovation Trends for 2025</h4>
                            <p className="post-excerpt">
                                Discover the latest home renovation trends that are defining 2025...
                            </p>
                            <button className="read-more">Read More</button>
                        </div>
                    </article>
                    <article className="post-card">
                        <img src="/api/placeholder/400/300" alt="Sample" className="post-image" />
                        <div className="post-content">
                            <h4 className="post-title">Minimalist Color Palettes</h4>
                            <p className="post-excerpt">
                                Explore how minimalist color schemes can transform your spaces...
                            </p>
                            <button className="read-more">Read More</button>
                        </div>
                    </article>
                </div>
            </section>

            <footer className="homepage-footer">
                <p>&copy; 2025 Seasons in Colour. All rights reserved.</p>
            </footer>

            {showLogin && (
                <div className="modal-overlay">
                    <div className="modal-content">
                        <button className="close-button" onClick={() => setShowLogin(false)}>X</button>
                        <h2>Login</h2>
                        <input type="email" placeholder="Email" className="modal-input" />
                        <input type="password" placeholder="Password" className="modal-input" />
                        <button className="modal-submit">Sign In</button>
                    </div>
                </div>
            )}
        </div>
    );
};

export default Homepage;

