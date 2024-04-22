import React, { useState, useEffect } from 'react';

const ScrollToTopButton = () => {
  const [isVisible, setIsVisible] = useState(false);

  useEffect(() => {
    // Add a scroll event listener to check scroll position
    const handleScroll = () => {
      const scrollY = window.scrollY;
      const threshold = 200; // Adjust this value to control when the button appears
      setIsVisible(scrollY > threshold);
    };

    window.addEventListener('scroll', handleScroll);

    return () => {
      window.removeEventListener('scroll', handleScroll);
    };
  }, []);

  const scrollToTop = () => {
    window.scrollTo({
      top: 0,
      behavior: 'smooth', // Add smooth scrolling
    });
  };

  return (
    <div
      className={`scroll-to-top ${isVisible ? 'visible' : 'hidden'}`}
      onClick={scrollToTop}
    >
      &#9650;
    </div>
  );
};

export default ScrollToTopButton;
