import React, { useState } from 'react';
import { Heart, MessageCircle, Share2, Calendar, User, Tag, Folder, ArrowLeft } from 'lucide-react';
import '../Styles/PostDetail.css';

const PostDetail = () => {
    const [isLiked, setIsLiked] = useState(false);
    const [likeCount, setLikeCount] = useState(142);
    const [comments, setComments] = useState([
        {
            id: 1,
            author: 'Sarah Johnson',
            content: 'This color palette is absolutely stunning! I\'ve been looking for inspiration for my living room and this is exactly what I needed.',
            time: '2 hours ago',
            avatar: '👩‍🦰'
        },
        {
            id: 2,
            author: 'Michael Chen',
            content: 'Great insights on seasonal color theory. The way you explained warm undertones vs cool undertones really clicked for me.',
            time: '5 hours ago',
            avatar: '👨‍💼'
        },
        {
            id: 3,
            author: 'Emma Wilson',
            content: 'Love the practical tips! Already ordered paint samples based on your recommendations. Can\'t wait to try this in my bedroom.',
            time: '1 day ago',
            avatar: '👩‍🎨'
        }
    ]);
    const [newComment, setNewComment] = useState('');

    const handleLike = () => {
        setIsLiked(!isLiked);
        setLikeCount(isLiked ? likeCount - 1 : likeCount + 1);
    };

    const handleAddComment = () => {
        if (newComment.trim()) {
            const comment = {
                id: comments.length + 1,
                author: 'You',
                content: newComment,
                time: 'Just now',
                avatar: '👤'
            };
            setComments([comment, ...comments]);
            setNewComment('');
        }
    };

    return (
        <div className="container">
            {/* Header */}
            <header className="header">
                <div className="header-content">
                    <div className="header-nav">
                        <div className="nav-left">
                            <button className="back-button">
                                <ArrowLeft size={20} />
                                <span>Back to Blog</span>
                            </button>
                        </div>
                        <h1 className="site-title">Seasons in Colour</h1>
                        <div className="nav-right">
                            <button className="share-button">
                                <Share2 size={20} />
                            </button>
                        </div>
                    </div>
                </div>
            </header>

            {/* Main Content */}
            <main className="main-content">
                {/* Post Header */}
                <article className="post-article">
                    <div className="post-hero">
                        <div className="hero-overlay"></div>
                        <div className="hero-content">
                            <div className="hero-tags">
                                <span className="tag-trends">Trends</span>
                                <span className="tag-color-theory">Color Theory</span>
                            </div>
                            <h1 className="post-title">
                                Spring Color Palette: Bringing Warmth and Energy to Your Home
                            </h1>
                        </div>
                    </div>

                    {/* Post Meta */}
                    <div className="post-meta">
                        <div className="meta-left">
                            <div className="meta-author">
                                <User size={16} />
                                <span>Isabella Rodriguez</span>
                            </div>
                            <div className="meta-date">
                                <Calendar size={16} />
                                <span>March 15, 2025</span>
                            </div>
                        </div>
                        <div className="meta-right">
                            <button
                                onClick={handleLike}
                                className={`like-button ${isLiked ? 'liked' : ''}`}
                            >
                                <Heart size={16} fill={isLiked ? 'currentColor' : 'none'} />
                                <span>{likeCount}</span>
                            </button>
                            <div className="comment-count">
                                <MessageCircle size={16} />
                                <span>{comments.length}</span>
                            </div>
                        </div>
                    </div>

                    {/* Post Content */}
                    <div className="post-content">
                        <p className="post-intro">
                            As we welcome the arrival of spring, it's time to refresh our living spaces with colors that reflect the season's vibrant energy and natural beauty. This comprehensive guide will help you incorporate the perfect spring palette into your home.
                        </p>

                        <h2 className="content-heading">Understanding Spring Colors</h2>
                        <p className="content-text">
                            Spring colors are characterized by their fresh, clean, and energizing qualities. Think of the first blooms of the season - soft pastels mixed with vibrant greens and sunny yellows. These colors have a warm undertone that brings life and optimism to any space.
                        </p>

                        <h2 className="content-heading">Key Colors for Spring 2025</h2>
                        <div className="color-grid">
                            <div className="color-card mint">
                                <div className="color-swatch mint-swatch"></div>
                                <h3 className="color-name">Fresh Mint</h3>
                                <p className="color-description">Perfect for bedrooms and bathrooms</p>
                            </div>
                            <div className="color-card yellow">
                                <div className="color-swatch yellow-swatch"></div>
                                <h3 className="color-name">Sunshine Yellow</h3>
                                <p className="color-description">Great for kitchens and dining areas</p>
                            </div>
                            <div className="color-card pink">
                                <div className="color-swatch pink-swatch"></div>
                                <h3 className="color-name">Coral Blush</h3>
                                <p className="color-description">Ideal for living rooms and entryways</p>
                            </div>
                        </div>

                        <h2 className="content-heading">Implementation Tips</h2>
                        <p className="content-text">
                            When incorporating spring colors into your home, start small. Add colorful throw pillows, artwork, or plants to test how the colors work in your space. Remember that lighting plays a crucial role in how colors appear throughout the day.
                        </p>

                        <p className="content-text">
                            For a cohesive look, choose one main spring color and support it with two complementary shades. This creates a harmonious palette that doesn't overwhelm the space while still bringing that fresh spring energy indoors.
                        </p>

                        <h2 className="content-heading">Seasonal Transition</h2>
                        <p className="content-text">
                            The beauty of spring colors is their versatility. Many of these shades can transition seamlessly into summer with the right accessories, making them a smart investment for your home's color scheme.
                        </p>
                    </div>

                    {/* Tags and Categories */}
                    <div className="post-tags">
                        <div className="categories">
              <span className="category-tag blue">
                <Folder size={14} />
                <span>Color Theory</span>
              </span>
                            <span className="category-tag green">
                <Folder size={14} />
                <span>Seasonal</span>
              </span>
                            <span className="category-tag purple">
                <Folder size={14} />
                <span>Trends</span>
              </span>
                        </div>
                        <div className="hashtags">
              <span className="hashtag">
                <Tag size={14} />
                <span>spring-colors</span>
              </span>
                            <span className="hashtag">
                <Tag size={14} />
                <span>home-decor</span>
              </span>
                            <span className="hashtag">
                <Tag size={14} />
                <span>interior-design</span>
              </span>
                            <span className="hashtag">
                <Tag size={14} />
                <span>2025-trends</span>
              </span>
                        </div>
                    </div>
                </article>

                {/* Comments Section */}
                <section className="comments-section">
                    <h2 className="comments-title">Comments ({comments.length})</h2>

                    {/* Add Comment Form */}
                    <div className="comment-form">
                        <div className="form-container">
                            <div className="form-avatar">
                                <div className="avatar-circle user-avatar">
                                    👤
                                </div>
                            </div>
                            <div className="form-input">
                <textarea
                    value={newComment}
                    onChange={(e) => setNewComment(e.target.value)}
                    placeholder="Share your thoughts on this post..."
                    className="comment-textarea"
                    rows="3"
                />
                                <div className="form-actions">
                                    <button
                                        onClick={handleAddComment}
                                        className="submit-button"
                                    >
                                        Post Comment
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>

                    {/* Comments List */}
                    <div className="comments-list">
                        {comments.map((comment) => (
                            <div key={comment.id} className="comment-item">
                                <div className="comment-avatar">
                                    <div className="avatar-circle comment-avatar-circle">
                                        {comment.avatar}
                                    </div>
                                </div>
                                <div className="comment-content">
                                    <div className="comment-header">
                                        <h4 className="comment-author">{comment.author}</h4>
                                        <span className="comment-time">{comment.time}</span>
                                    </div>
                                    <p className="comment-text">{comment.content}</p>
                                </div>
                            </div>
                        ))}
                    </div>
                </section>
            </main>
        </div>
    );
};

export default PostDetail;