import { gsap } from "gsap";
import { useEffect, useRef, useState } from "react";
import { home, footer, planner } from "../assets/images/index.js";
import Hoc from "./HOC/SectionWrapper.jsx";
import { ScrollTrigger } from "gsap/ScrollTrigger.js";
import { TweenLite } from "gsap/gsap-core.js";
import { SplitText } from "gsap-trial/SplitText";
import useWindowDimensions from "../utils/useWindowDemensions.js";
const Portfolio = () => {
  const { height, width } = useWindowDimensions();
  const portfolioRef = useRef(null);
  const titleRef = useRef(null);
  const [imgState, setImgState] = useState({
    current: 0,
    imgs: [home, footer, planner],
  });
  useEffect(() => {
    gsap.registerPlugin(ScrollTrigger);
    gsap.registerPlugin(SplitText);

    gsap.fromTo(
      portfolioRef.current,
      {
        y: 300,
        opacity: 0,
      },
      {
        y: 0,
        opacity: 1,
        duration: 1,
        scrollTrigger: {
          trigger: portfolioRef.current,
        },
      }
    );
    const mySplitText = new SplitText(titleRef.current, {
      type: "words,chars",
      charsClass: "special",
    });
    const chars = mySplitText.chars;
    TweenLite.set(titleRef.current, {
      perspective: 400,
    });
    gsap.from(chars, {
      opacity: 0,
      y: 50,
      ease: "back(4)",
      stagger: 0.05,
      scrollTrigger: {
        trigger: portfolioRef.current,
      },
    });
    const interval = setInterval(setUrl, 6000);

    return () => clearInterval(interval);
  }, []);
  const setUrl = () => {
    setImgState((prevState) => {
      if (prevState.current < prevState.imgs.length - 1) {
        return { ...prevState, current: prevState.current + 1 };
      } else {
        return { ...prevState, current: 0 };
      }
    });
  };
  const prevUrl = () => {
    setImgState((prevState) => {
      if (prevState.current > 0) {
        return { ...prevState, current: prevState.current - 1 };
      } else {
        return { ...prevState, current: prevState.imgs.length - 1 };
      }
    });
  };

  return (
    <>
      <div className="flex w-full items-center flex-col">
        <h1 className="mb-40" ref={titleRef}>
          Mijn{" "}
          <span className="text-tertiary pulsating-text mx-[5px]">
            portfolio
          </span>
        </h1>
        <div
          className={`w-full relative px-20 text-[70px] md:text-[100px] text-center flex justify-center items-center`}
          ref={portfolioRef}
        >
          <div
            className="cursor-pointer text-white arrow select-none"
            onClick={prevUrl}
          >
            &#11164;
          </div>
          <img
            className="w-[65%]"
            src={imgState.imgs[imgState.current]}
            alt=""
          />
          <p
            className="cursor-pointer text-white arrow select-none"
            onClick={setUrl}
          >
            &#11166;
          </p>
        </div>
      </div>

      <style jsx>{`
        .arrow {
          width: 10%; /* adjust this as needed */
        }
        .arrow:hover {
          color: rgb(226 232 240);
        }
        @media screen and (max-width: 768px) {
          .arrow {
            width: 20%; /* adjust this as needed */
          }
        }
      `}</style>
    </>
  );
};
export default Hoc(Portfolio);
