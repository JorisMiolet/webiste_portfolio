import DevTeam from "./lottieAnimations/devTeam"
import Dollar from "./lottieAnimations/dollar"
import Smile from "./lottieAnimations/smile"
import { gsap } from "gsap"
import { ScrollTrigger } from "gsap/all"
import { useEffect, useRef } from "react"
import { Hoc } from "./HOC"
gsap.registerPlugin(ScrollTrigger);
const Usp = () => {
  const textAnimation = useRef(null);
  const questionMarkRef = useRef(null);
  const divRef = useRef(null);

  useEffect(() => {
    const mainElement = textAnimation.current;

    const mainTextTimeline = gsap.timeline({
      defaults: {
        duration: 1,
      },
      scrollTrigger: {
        trigger: mainElement,
        start: "top center",
      },
    });

    mainTextTimeline.fromTo(
      mainElement,
      {
        y: -200,
        opacity: 0,
      },
      {
        y: 0,
        opacity: 1,
      }
    );

    // Timeline for the question mark
    const questionMarkTimeline = gsap.timeline({
      defaults: {
        duration: 1.5,
      },
      scrollTrigger: {
        trigger: textAnimation.current,
        start: "top center", // Adjust as needed
      },
    });

    questionMarkTimeline.fromTo(
      questionMarkRef.current,
      {
        x: 600, // Adjust to come from the right
        opacity: 0,
        rotation: 720,
      },
      {
        x: 0,
        opacity: 1,
        rotation: 0,
        delay: 1
      },
      "-=0.5" // Adjust the delay to synchronize the animations
    );

    gsap.fromTo(divRef.current, {
      scale: 0.2,
      autoAlpha: 0,   
    },{
      scale: 1,
      autoAlpha: 1,
      duration: 1,
      scrollTrigger: {
        trigger: divRef.current
      }
    })
  }, []);


    return (
        <div className=" flex flex-col items-center justify-center" >
            <div className="flex mb-10 ">

            <h1 className="uppercase text-[1.3rem] sm:text-[2rem] md:text-[2.8rem]" id="title" ref={textAnimation}>
                Waarom 
                <span className="text-tertiary pulsating-text mx-[5px]">
                    JM Websolutions
                    </span>
                    
            </h1>
            <span id="questionmark" className="text-[1.3rem] sm:text-[2rem] md:text-[2.8rem] text-white"  ref={questionMarkRef}>?</span>
            </div>

            <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-8 px-20 pb-40 pt-40 lg:h-[90vh]" ref={divRef}>
                <div className="flex flex-col items-center">
                 <DevTeam />
                 <div className="mt-[100px]">
                    <h2 className="text-white">Ik streef altijd naar het beste resultaat.</h2>
                    <p className="text-white "> Ik vind het belangrijk dat mijn klanten tevreden zijn over het product, daarom streef ik altijd naar een
                    resultaat dat boven de verwachtingen van de klant uit komt. Ik bereik dit met mijn expertise in gepersonaliseerde websites
                    en ervaring met het ontwikkelen van geavanceerde websites. 
                    </p>
                 </div>
                </div>
                <div className="flex flex-col items-center">
                    <Dollar />
                    <div className="mt-[88px]">
                    <h2 className="text-white">Ik zorg voor de optimale waarde voor uw geld.</h2>
                    <p className="text-white ">Voor mij is het belangrijk dat klanten het gevoel hebben dat hun geld goed is besteed. Met die rede
                      ben ik er op gefocust om mijn product voor de meest optimale prijs te leveren.
                      Ik vraag daarom ook een prijs per product in plaats van per uur, zodat als het voorkomt dat ik langer bezig ben met een project dan verwacht,
                      de klant daar niet extra voor hoeft te betalen.
                    </p>
                 </div>
                </div>
                <div className="flex flex-col  items-center">
                    <Smile />
                    <div>
                    <h2 className="text-white">Ik ben gefocust op een goede samenwerking met mijn klanten.</h2>
                    <p className="text-white"> Ik vind het belangrijk dat tijdens het project de klant de samenwerking goed ervaard. Ik heb passie voor mijn werk en ben zelf
                    altijd erg enthausiast over mijn projecten. Dit probeer ik ook uit te stralen. Ik hecht er waarde aan dat naast dat de klant 
                    tevreden is over het project, ze ook tevreden zijn over mij. 
                    </p>
                 </div>
                </div>
            </div>
        </div>
    )
}
export default Hoc(Usp);