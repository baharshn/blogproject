import React, {useEffect, useState} from 'react';
import { Search, User, X, Mail, Lock, Eye, EyeOff, Calendar, ArrowRight, Facebook, Twitter, Instagram, Youtube,UserPlus } from 'lucide-react';
import '../Styles/BlogHomePage.css';
import axios from 'axios';
import { Link } from "react-router-dom";

const HomePage = () => {
    console.log("Rendering BlogHomepage!");
    const [showLogin, setShowLogin] = useState(false);
    const [showRegister, setShowRegister] = useState(false);
    const [showPassword, setShowPassword] = useState(false);
    const [showConfirmPassword, setShowConfirmPassword] = useState(false);
    const [searchQuery, setSearchQuery] = useState('');
    const [selectedRole, setSelectedRole] = useState('Reader');
    const [blogPosts, setBlogPosts] = useState([]);


    //postları backendden çekiyor ama image kısmı düşünülmeli?
    useEffect(() => {
        axios.get('http://localhost:8080/api/posts')
            .then(res => {
                console.log("API response:", res.data);
                setBlogPosts(res.data);
            })
            .catch(err => console.error('Error fetching posts:', err));
    }, []);




    //mock data
    /*
    const blogPosts = [
        {
            id: 1,
            title: "Cottage Aesthetic for cosy living",
            content: "If you love the comfort of cosy nooks, the beauty of floral patterns, and the calming colors found in nature, the cottage aesthetic could be the perfect style for your home.",
            author_username:"Bahar Şahin",
            categoryNames: ["Styling"],
            tagNames:["minimal","2025"],
            createdAt: "2025-04-01T10:00:00",
            image: "/api/placeholder/400/300",

        },
        {
            id: 2,
            title: "Home Renovation Trends for 2025: What's In and What's Out",
            content: "Discover the latest home renovation trends that are defining 2025. From sustainable materials to bold color choices, learn what's trending this year.",
            author_username:"Selin Bayır",
            categoryNames: ["Trends"],
            tagNames:["minimal","2025"],
            createdAt: "May 2025",
            image: "/api/placeholder/400/300",

        },
        {
            id: 3,
            title: "The 2025 Trends Guide is here: Bathrooms, Kitchens & more",
            content: "Get the complete guide to interior design trends for 2025. Transform your spaces with the latest in bathroom and kitchen design.",
            author_username:"Elif Temizel",
            categoryNames: ["Trends","Home"],
            tagNames:["minimal","2025"],
            createdAt: "April 2025",
            image: "/api/placeholder/400/300",

        },
        {
            id: 4,
            title: "Minimalist Color Palettes That Transform Spaces",
            content: "Explore how carefully chosen minimalist color schemes can completely transform your living spaces with subtle elegance.",
            author_username:"Nehir Alaftan",
            categoryNames: ["Color Theory"],
            tagNames:["minimal","2025"],
            createdAt: "June 2025",
            image: "/api/placeholder/400/300",

        }
    ];

     */





    const categories = ["All", "Styling", "Trends", "Color Theory", "Renovation", "Seasonal"];

    const LoginModal = () => {
        const [loginEmail, setLoginEmail] = useState('');
        const [loginPassword, setLoginPassword] = useState('');
        const [loginError, setLoginError] = useState('');

        const handleLogin = async () => {
            setLoginError('');
            try {
                const response = await axios.post('http://localhost:8080/api/auth/login', {
                    username: loginEmail,
                    password: loginPassword
                });

                const token = response.data.token;

                if (token) {
                    localStorage.setItem('token', token);
                    setShowLogin(false); // modal'ı kapat
                    alert('Login successful!');
                } else {
                    setLoginError('Invalid credentials');
                }
            } catch (error) {
                console.error('Login failed:', error);
                setLoginError('Login failed. Please check your credentials.');
            }
        };


        return (
            <div className="modal-overlay">
                <div className="modal-container">
                    <button
                        onClick={() => setShowLogin(false)}
                        className="modal-close"
                    >
                        <X size={24}/>
                    </button>

                    <div className="modal-header">
                        <h2>Welcome Back</h2>
                        <p>Sign in to your account</p>
                    </div>

                    <div className="modal-form">
                        <div className="input-group">
                            <Mail className="input-icon" size={20}/>
                            <input
                                type="email"
                                placeholder="Email address"
                                className="input-field"
                                value={loginEmail}
                                onChange={(e)=>setLoginEmail(e.target.value)}
                            />
                        </div>

                        <div className="input-group">
                            <Lock className="input-icon" size={20}/>
                            <input
                                type={showPassword ? "text" : "password"}
                                placeholder="Password"
                                className="input-field"
                                value={loginPassword}
                                onChange={(e) => setLoginPassword(e.target.value)}
                            />
                            <button
                                type="button"
                                onClick={() => setShowPassword(!showPassword)}
                                className="password-toggle"
                            >
                                {showPassword ? <EyeOff size={20}/> : <Eye size={20}/>}
                            </button>
                        </div>

                        <div className="form-options">
                            <label className="checkbox-label">
                                <input type="checkbox"/>
                                <span>Remember me</span>
                            </label>
                            <a href="#" className="forgot-password">Forgot password?</a>
                        </div>

                        {loginError && (
                            <div className="error-message">{loginError}</div>
                        )}

                        <button className="btn btn-primary btn-full" onClick={handleLogin}>
                            Sign In
                        </button>
                    </div>

                    <div className="modal-footer">
                        <span>Don't have an account? </span>
                        <button
                            onClick={() => {
                                setShowLogin(false);
                                setShowRegister(true);
                            }}
                            className="link-button"
                        >
                            Sign up
                        </button>
                    </div>
                </div>
            </div>
        );
    }

    const RegisterModal = () => {
        const [registerEmail, setRegisterEmail] = useState('');
        const [registerPassword, setRegisterPassword] = useState('');
        const [registerConfirmPassword, setRegisterConfirmPassword] = useState('');
        const [registerFullName,setRegisterFullName]=useState('');
        const [registerUsername,setRegisterUsername]= useState('');
        const [selectedRole, setSelectedRole] = useState(3);
        const [emailError, setEmailError] = useState('');
        const [passwordError, setPasswordError] = useState('');
        const [confirmPasswordError, setConfirmPasswordError] = useState('');
        const [registerError,setRegisterError] = useState('');


        const handleRegister = async () => {

            setEmailError('');
            setPasswordError('');
            setConfirmPasswordError('');
            setRegisterError('');

            let hasError = false;

            // Email geçerli mi?
            const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            if (!emailRegex.test(registerEmail)) {
                setEmailError('Please enter a valid email address.');
                hasError=true;
            }

            // Şifre uzunluğu
            if (registerPassword.length < 6) {
                setPasswordError('Password must be at least 6 characters.');
                hasError=true;
            }

            // Şifreler eşleşiyor mu
            if (registerPassword !== registerConfirmPassword) {
                setConfirmPasswordError('Passwords do not match.');
                hasError=true;
            }

            if(hasError){
                return;
            }

            // Tüm validation geçtiyse
            console.log('REGISTER:', {
                email: registerEmail,
                password: registerPassword,
                fullName: registerFullName,
                username: registerUsername,
                roleId: selectedRole
            });

            try {
                const response = await axios.post('http://localhost:8080/api/auth/register', {
                    email: registerEmail,
                    password: registerPassword,
                    fullName: registerFullName,
                    username: registerUsername,
                    roleId: selectedRole
                });

                const token = response.data.token;

                if ((response.status === 200 || response.status === 201)) {
                    //localStorage.setItem('token', token);
                    setShowRegister(false); // modal'ı kapat
                    alert('Register successful!');
                } else {
                    setRegisterError('Invalid credentials');
                }
            } catch (error) {
                console.error('Register failed:', error);
                setRegisterError('Register failed. Please check your credentials.');
            }


        };

        return (
            <div className="modal-overlay">
                <div className="modal-container">
                    <button
                        onClick={() => setShowRegister(false)}
                        className="modal-close"
                    >
                        <X size={24}/>
                    </button>

                    <div className="modal-header">
                        <h2>Create Account</h2>
                        <p>Join our community of design enthusiasts</p>
                    </div>

                    <div className="modal-form">
                        <div className="name-inputs">
                            <UserPlus className="input-icon" size={20}/>
                            <input
                                type="text"
                                placeholder="Full name"
                                className="input-field"
                                value={registerFullName}
                                onChange={(e) => setRegisterFullName(e.target.value)}
                            />

                        </div>

                        <div className="input-group">
                            <UserPlus className="input-icon" size={20}/>
                            <input
                                type="text"
                                placeholder="Username"
                                className="input-field"
                                value={registerUsername}
                                onChange={(e) => setRegisterUsername(e.target.value)}
                            />
                            {emailError && (
                                <div className="error-message">{emailError}</div>
                            )}
                        </div>

                        <div className="input-group">
                            <Mail className="input-icon" size={20}/>
                            <input
                                type="email"
                                placeholder="Email address"
                                className="input-field"
                                value={registerEmail}
                                onChange={(e) => setRegisterEmail(e.target.value)}
                            />
                            {emailError && (
                                <div className="error-message">{emailError}</div>
                            )}
                        </div>

                        <div className="input-group">
                            <Lock className="input-icon" size={20}/>
                            <input
                                type={showPassword ? "text" : "password"}
                                placeholder="Password"
                                className="input-field"
                                value={registerPassword}
                                onChange={(e) => setRegisterPassword(e.target.value)}

                            />

                            <button
                                type="button"
                                onClick={() => setShowPassword(!showPassword)}
                                className="password-toggle"
                            >
                                {showPassword ? <EyeOff size={20}/> : <Eye size={20}/>}
                            </button>
                            {passwordError && (
                                <div className="error-message">{passwordError}</div>
                            )}
                        </div>

                        <div className="input-group">
                            <Lock className="input-icon" size={20}/>
                            <input
                                type={showConfirmPassword ? "text" : "password"}
                                placeholder="Confirm password"
                                className="input-field"
                                value={registerConfirmPassword}
                                onChange={(e) => setRegisterConfirmPassword(e.target.value)}
                            />
                            <button
                                type="button"
                                onClick={() => setShowConfirmPassword(!showConfirmPassword)}
                                className="password-toggle"
                            >
                                {showConfirmPassword ? <EyeOff size={20}/> : <Eye size={20}/>}
                            </button>
                            {confirmPasswordError && (
                                <div className="error-message">{confirmPasswordError}</div>
                            )}
                        </div>

                        <div className="input-group">
                            <label className="role-label">Select Role</label>
                            <select
                                value={selectedRole}
                                onChange={(e) => setSelectedRole(parseInt(e.target.value))}
                                className="role-select"
                            >
                                <option value={1}>Admin</option>
                                <option value={3}>Reader</option>
                            </select>
                        </div>

                        <label className="checkbox-label terms">
                            <input type="checkbox"/>
                            <span>I agree to the Terms of Service and Privacy Policy</span>
                        </label>

                        {registerError && (
                            <div className="error-message">{registerError}</div>
                        )}

                        <button className="btn btn-primary btn-full"
                                onClick={handleRegister}>
                            Create Account
                        </button>
                    </div>

                    <div className="modal-footer">
                        <span>Already have an account? </span>
                        <button
                            onClick={() => {
                                setShowRegister(false);
                                setShowLogin(true);
                            }}
                            className="link-button"
                        >
                            Sign in
                        </button>
                    </div>
                </div>
            </div>
        );
    }

    return (
        <>
            {console.log("RENDER blogPosts:", blogPosts)}
            <div className="blog-homepage">

                {/* Header */}
                <header className="header">
                    <div className="container">
                        <div className="header-content">
                            <div className="header-left">
                                <h1 className="logo">Seasons in Colour</h1>
                                <nav className="nav">
                                    <a href="#" className="nav-link">HOME</a>
                                    <a href="#" className="nav-link">ABOUT</a>
                                    <a href="#" className="nav-link">BLOG</a>
                                    <a href="#" className="nav-link">CONTACT</a>
                                </nav>
                            </div>

                            <div className="header-right">
                                <div className="search-box">
                                    <Search className="search-icon" size={20} />
                                    <input
                                        type="text"
                                        placeholder="Search..."
                                        value={searchQuery}
                                        onChange={(e) => setSearchQuery(e.target.value)}
                                        className="search-input"
                                    />
                                </div>

                                <button
                                    onClick={() => setShowLogin(true)}
                                    className="login-btn"
                                >
                                    <User size={20} />
                                    <span>Login</span>
                                </button>
                            </div>
                        </div>
                    </div>
                </header>



                {/* Hero Section */}
                <section className="hero">
                    <div className="hero-bg"></div>
                    <div className="container">
                        <div className="hero-content">
                            <h2 className="hero-title">Seasons in Colour</h2>
                            <p className="hero-subtitle">Get the latest trends from the Seasonsincolour interiors blog</p>
                            <div className="hero-search">
                                <Search className="hero-search-icon" size={24} />
                                <input
                                    type="text"
                                    placeholder="Search for inspiration..."
                                    className="hero-search-input"
                                />
                            </div>
                        </div>
                    </div>
                </section>

                {/* Category Filter */}
                <section className="categories">
                    <div className="container">
                        <div className="category-buttons">
                            {categories.map((category) => (
                                <button
                                    key={category}
                                    className="category-btn"
                                >
                                    {category}
                                </button>
                            ))}
                        </div>
                    </div>
                </section>

                {/* Featured Post */}
                <section className="featured">
                    <div className="container">
                        <div className="featured-content">
                            <div className="featured-text">
                                <div className="featured-date">
                                    <Calendar size={16} />
                                    <span>May 2025</span>
                                </div>
                                <h3 className="featured-title">
                                    The 2025 Trends Guide is here<br />
                                    Bathrooms, Kitchens & more
                                </h3>
                                <p className="featured-excerpt">
                                    Discover the complete guide to interior design trends for 2025. From sustainable materials to bold color choices, learn what's defining this year's most sought-after designs.
                                </p>
                                <button className="btn btn-primary">
                                    <span>Read More</span>
                                    <ArrowRight size={20} />
                                </button>
                            </div>
                            <div className="featured-image">
                                <img
                                    src="/api/placeholder/600/400"
                                    alt="Featured post"
                                />
                            </div>
                        </div>
                    </div>
                </section>

                {/* Blog Posts Grid */}


                <section className="blog-posts">
                    <div className="container">
                        <h3 className="section-title">Latest Stories</h3>
                        <pre style={{ background: 'yellow', padding: '1rem' }}>
  {blogPosts.length > 0
      ? JSON.stringify(blogPosts, null, 2)
      : "Henüz yüklenmedi veya boş!"}
</pre>
                        <div className="posts-grid">
                            {blogPosts.length > 0 ? (
                                blogPosts.map((post) => {
                                    console.log("Mapping post:", post);
                                    return (
                                        <article key={post.id} className="post-card">
                                            <Link to={`/posts/${post.id}`??'#'}>
                                                <img

                                                    src={post.image ?? '/placeholder.jpg'}
                                                    alt={post.title ?? 'No title'}
                                                    className="post-image"
                                                />
                                                <div className="post-content">
                                                    <div className="post-meta">
                                                        <div className="post-categories">
                                                            {(post.categoryNames ?? []).map((cat, idx) => (
                                                                <span key={`${cat}-${idx}`} className="category">{cat}</span>
                                                            ))}
                                                        </div>
                                                        <span className="post-read-time">{post.author_username??"Unknown"}</span>
                                                    </div>
                                                    <h4 className="post-title">{post.title ?? "untitled"}</h4>
                                                    <p className="post-excerpt">{post.content ?? ""}</p>
                                                    <div className="post-footer">
                                                        <span className="post-date">{post.createdAt ?? ""}</span>
                                                        <button className="read-more">
                                                            Read More
                                                        </button>
                                                    </div>
                                                </div>
                                            </Link>
                                        </article>
                                    );
                                })
                            ) : (
                                <p style={{ background: 'pink', padding: '1rem' }}>
                                    Gösterilecek post yok veya yükleniyor...
                                </p>
                            )}
                            {/*
                        {blogPosts.map((post) => (

                            <article key={post.id } className="post-card">
                                <Link to={`/posts/${post.id}`??'#'}>
                                <img
                                    src={post.image ?? '/placeholder.jpg'}
                                    alt={post.title ?? 'No title'}
                                    className="post-image"
                                />
                                <div className="post-content">
                                    <div className="post-meta">
                                        <div className="post-categories">
                                            {(post.categoryNames ?? []).map((cat, idx) => (
                                                <span key={`${cat}-${idx}`} className="category">{cat}</span>
                                            ))}
                                        </div>
                                        <span className="post-read-time">{post.author_username??"Unknown"}</span>
                                    </div>
                                    <h4 className="post-title">{post.title ?? "untitled"}</h4>
                                    <p className="post-excerpt">{post.content ?? ""}</p>
                                    <div className="post-footer">
                                        <span className="post-date">{post.createdAt ?? ""}</span>
                                        <button className="read-more">
                                            Read More
                                        </button>
                                    </div>
                                </div>
                                </Link>
                            </article>
                        ))}
                    */}
                        </div>

                    </div>
                </section>



                {/* Newsletter Section */}
                <section className="newsletter">
                    <div className="container">
                        <h3 className="newsletter-title">Stay Inspired</h3>
                        <p className="newsletter-subtitle">Get the latest design trends and inspiration delivered to your inbox</p>
                        <div className="newsletter-form">
                            <input
                                type="email"
                                placeholder="Enter your email"
                                className="newsletter-input"
                            />
                            <button className="btn btn-primary">
                                Subscribe
                            </button>
                        </div>
                    </div>
                </section>

                {/* Footer */}
                <footer className="footer">
                    <div className="container">
                        <div className="footer-content">
                            <div className="footer-section">
                                <h4 className="footer-title">Seasons in Colour</h4>
                                <p className="footer-description">
                                    Your ultimate destination for interior design inspiration and seasonal color trends.
                                </p>
                            </div>

                            <div className="footer-section">
                                <h5 className="footer-heading">Quick Links</h5>
                                <ul className="footer-links">
                                    <li><a href="#">Home</a></li>
                                    <li><a href="#">About</a></li>
                                    <li><a href="#">Blog</a></li>
                                    <li><a href="#">Contact</a></li>
                                </ul>
                            </div>

                            <div className="footer-section">
                                <h5 className="footer-heading">Categories</h5>
                                <ul className="footer-links">
                                    <li><a href="#">Styling</a></li>
                                    <li><a href="#">Trends</a></li>
                                    <li><a href="#">Color Theory</a></li>
                                    <li><a href="#">Renovation</a></li>
                                </ul>
                            </div>

                            <div className="footer-section">
                                <h5 className="footer-heading">Follow Us</h5>
                                <div className="social-links">
                                    <a href="#" className="social-link">
                                        <Facebook size={20} />
                                    </a>
                                    <a href="#" className="social-link">
                                        <Instagram size={20} />
                                    </a>
                                    <a href="#" className="social-link">
                                        <Twitter size={20} />
                                    </a>
                                    <a href="#" className="social-link">
                                        <Youtube size={20} />
                                    </a>
                                </div>
                            </div>
                        </div>

                        <div className="footer-bottom">
                            <p>&copy; 2025 Seasons in Colour. All rights reserved.</p>
                        </div>
                    </div>
                </footer>

                {/* Modals */}
                {showLogin && <LoginModal />}
                {showRegister && <RegisterModal />}
            </div>
        </>
    );

};

export default HomePage;

