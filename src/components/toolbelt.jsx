import { useEffect, useRef } from "react"
import { Hoc } from "./HOC"
import { gsap } from "gsap"
import {ScrollTrigger} from 'gsap/ScrollTrigger.js'
import { SplitText } from "gsap-trial/SplitText"
import { imagearray} from "../assets/images"
import useWindowDimensions from "../utils/useWindowDemensions.js";



const Toolbelt = () => {
    const headerRef = useRef(null)
    const divRef = useRef(null)
    const pictureRef = useRef([])
    const { height, width } = useWindowDimensions();
    

    useEffect(() => {
        gsap.registerPlugin(ScrollTrigger)
        gsap.registerPlugin(SplitText)
        const splitWords = new SplitText(headerRef.current, {type: "words"})
        const words = splitWords.words;

        const timeline = gsap.timeline({
            duration: 1.5,
            delay: 0.5 ,
            scrollTrigger: {
                trigger: divRef.current
            }
        });
        timeline.fromTo(words[0], {
            x: -1000,
            autoAlpha: 0
        },{
           x: 0,
           autoAlpha: 1,
           ease: "bounce.out" 
        }).fromTo(words[1],
        {
            x: 1000,
            autoAlpha: 0
        },{
           x: 0,
           autoAlpha: 1,
           ease: "bounce.out"  
        }).fromTo(words[2], {
            y: -1000,
            autoAlpha: 0
        },{
           y: 0,
           autoAlpha: 1 ,
           ease: "power.out"
        })

       


        
    }, [])

    return (
        <>
            <div className=" w-[1000px] lg:w-[2048px] items-center flex flex-col">
                <h1 className="mb-10" ref={headerRef}>Veel gebruikte <span className="text-tertiary pulsating-text mx-[5px]">tools</span></h1>
                <div className="bg-primary h-80 w-[1000px] lg:w-[2048px] glow flex relative" ref={divRef}>
                <div className="conveyor-belt">
                {imagearray.map((imageSrc, index) => (
                    <img key={index} src={imageSrc} className="conveyor-belt__item" alt={`Image ${index + 1}`} />
                ))}
            </div>
                </div>
            </div>
            <style jsx>{`
                .conveyor-belt {
                    display: flex;
                    overflow: hidden;
                    animation: conveyor 20s linear infinite;
                }
                
                .conveyor-belt__item {
                    flex-shrink: 0;
                    width: 200px;
                    height: 200px;
                    margin-right: 20px;
                }
                
                @keyframes conveyor {
                    0% {
                        transform: translateX(0);
                    }
                    100% {
                        transform: translateX(-100%);
                    }
                }
                
                @media (max-width: 1000px) {
                    .conveyor-belt__item {
                        width: 150px;
                        height: 150px;
                    }
                }
                
            `}</style>
        </>
    )
}
export default Hoc(Toolbelt)