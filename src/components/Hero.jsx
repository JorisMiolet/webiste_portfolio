import Typewriter from "typewriter-effect"
import Logo from "./canvas/logo"
import Tilt from "react-parallax-tilt"
import { fotoJ } from "../assets/images";
import { useEffect, useRef, useState } from 'react';
import { gsap } from 'gsap';
import { v4 as uuidv4 } from 'uuid'; 
import MyShaderGradient from "./MyShaderGradient";

  

const Hero = ({OnButtonClick}) => {
  
    return(
        <>
            <div className="w-full flex flex-col items-center justify-center pb-[200px]">
                <MyShaderGradient />
                <div className="flex w-full items-center justify-center">
                    <Logo/>
                </div>
                <div className="flex w-full flex-col justify-center  lg:flex-row">
                    <div className="flex-1 flex justify-center lg:justify-end">
                        <p className="text-[3rem] md:text-[4rem] text-white">Ik ben een:</p>
                    </div>
                <div className="flex-1 text-center mt-10 lg:text-left lg:mt-0">
                    <Typewriter 
                        onInit={(typewriter) => {
                            typewriter
                            .typeString(`<span class="typewriter-style">Progammeur</span>`)
                            .pauseFor(1000)
                            .deleteAll()
                            .typeString(`<span class="typewriter-style">Designer</span>`)
                            .pauseFor(1000)
                            .deleteAll()
                            .typeString(`<span class="typewriter-style">Problem solver</span>`)
                            .pauseFor(1000)
                            .start()
                        }}  
                        options={{loop: true}}
                        />
                </div>
                </div>
                <Tilt tiltReverse className="px-4 sm:px-0">
                    <button className="transition hoverButton py-4 px-8 bg-none text-white rounded-full mt-8 text-[1.5em] sm:text-[2em] sm:py-8 sm:px-16
                    drop-shadow-xl hover:drop-shadow-2xl active:drop-shadow-none border-8 border-quartiary "
                    onClick={() => OnButtonClick('cta')}
                    >
                        Stuur mij hier een mailtje
                    </button>
                </Tilt>
            </div>
            <style>{`
              .hoverButton {
                background-image: linear-gradient(to right, #45AA69 50%, transparent 50%);
                background-size: 200% 100%;
                background-position: right bottom;
                transition: all .2s ease-out;
              }
              
              .hoverButton:hover {
                background-position: left bottom;
              }
              
              
            `}</style>
        </>
    )
}
export default Hero