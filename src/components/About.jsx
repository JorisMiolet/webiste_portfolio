import { Hoc } from "./HOC";
import { fotoJ } from "../assets/images";
import { useEffect, useRef } from "react";
import gsap from "gsap";

const About = () => {
    const headerRef = useRef(null);
    const divRef = useRef(null);
    useEffect(() => {
        gsap.fromTo(headerRef.current, {
            x: 800,
            opacity: 0,
        },{
            x: 0,
            opacity: 1,
            scrollTrigger:{
                trigger: divRef.current,
            }
        })
        gsap.fromTo(divRef.current, {
            y: 500,
            opacity: 0
        },{
            y: 0,
            opacity: 1,
            duration: 1,
            scrollTrigger:{
                trigger: divRef.current
            }
        })
    }, [])
    return (
        <div className="flex items-center flex-col">
        <h1 className=" z-[10] mb-10" ref={headerRef}>Wie ben <span className="text-tertiary pulsating-text mx-[5px]">ik</span>?</h1>
        <div className="min-h-[90vh] py-16 flex flex-col md:flex-row  items-center justify-around px-20" ref={divRef}>
            <div className="4-[30%]">
                <img src={fotoJ} className="h-full" alt="foto van Mij" />
            </div>
            <div className="w-full md:w-[40%] ml-8 mt-12 md:mt-0">
                <p className="text-white">
                    Mijn naam is Joris Mioulet. Ik ben 19 jaar oud en een Full Stack Web Developer. Ik ben geobsedeerd door personlijke groei
                    en ben hier ook constant mee bezig in de vorm van sport, zelfstudie en business. Daarnaast hou ik van muziek wat ik zelf ook maak. Ik ben JM WebSolutions ook gestart
                    met de rede dat je oneindig kan blijven groeien in je eigen bedrijf en dat intereseerde mij al vanaf een jonge leeftijd.
                    Naast personlijke groei, hou ik ook van resultaten en ik probeer altijd het beste uit mezelf te halen. Ik vind het ook leuk om andere te zien groeien
                    en probeer de mensen om me heen te helpen waar het kan. Ondertussen heb ik al 2 jaar ervaring met programmeren en web development en ik blijf het met de dag leuker vinden.
                </p>
            </div>

        </div>
        </div>
    )
}
export default Hoc(About);