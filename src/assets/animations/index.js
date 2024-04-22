const fadeIn = (duration, delay, direction, targetElement) => {
    return {
        opacity: 0,
        x: direction === "left" ? "100vw" : direction === "right" ? "-100vw" : 0,
        y: direction === "up" ? "100vh" : direction === "down" ? "-100vh" : 0,
        duration: duration,
        delay: delay,
        ease: "power.out",
        scrollTrigger: {
            trigger: targetElement,
        },
    };
};
export {
    fadeIn
}