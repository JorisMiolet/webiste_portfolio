import { laptop, htmlJsCss, heart_pc } from "../assets/images";
import { bulletPoints } from "../assets/constants";
import { gsap } from "gsap";
import { useEffect, useRef } from "react";
import { Hoc } from "./HOC";
const BulletPoint = ({ bulletpoint, imageRefs ,title, content, index }) => {

  
  const lineRefs = useRef([]);
  const bulletPointRefs = useRef([]);
  const lastBulletPoint = bulletPoints.length - 1;
  useEffect(() => {
      
      const runTimeline = () => {
        const timeline = gsap.timeline();
        //reset the last bulletpoint
        gsap.set(bulletPointRefs.current[2], {borderColor: "#9d9d9d"})
        //set first bulletpoint
        timeline.set(bulletPointRefs.current[0], {borderColor: "#AA4586",});
       
        //set first image
        timeline.fromTo(imageRefs.current[0], {
          y:200,
          opacity: 0,
          display: "none",
        },
        {
          y: 0,
          opacity: 1,
          display: "block",
        })
        //start first line animation
        timeline.to(lineRefs.current[0], {
          duration: 5,
          ease: 'linear',
          height: 95,
        });
        
        //set second bulletpoint
        timeline.set(bulletPointRefs.current[1], {borderColor: "#AA4586",}); 
        //reset first bulletpoint 
        timeline.set(bulletPointRefs.current[0], {borderColor: "#9d9d9d",});
        timeline.to(lineRefs.current[0], {
          height: 0,
          duration: 0,
        });
        timeline.to(imageRefs.current[0], {
          y:-200,
          opacity: 0,
          display: "none",
        })
        timeline.fromTo(imageRefs.current[1], {
          y:200,
          opacity: 0,
          display: "none",
        },
        {
          y: 0,
          opacity: 1,
          display: "block",
        })
        //reset first line length
        
        //start second line animation
        timeline.to(lineRefs.current[1], {
          duration: 5,
          ease: 'linear',
          height: 95,
        });
        //reset first line length
        
        //reset second bulletpoint
        timeline.set(bulletPointRefs.current[1], {
          borderColor: "#9d9d9d",
        });
        timeline.to(lineRefs.current[1], {
          duration: 0,
          height: 0,
        });
        
        //set last bulletpoint
        timeline.set(bulletPointRefs.current[2], {borderColor: "#AA4586" });
        
        timeline.to(imageRefs.current[1],{
              y:-200,
              opacity: 0,
              display: "none",
            })
            timeline.fromTo(imageRefs.current[2], {
              y:200,
              opacity: 0,
              display: "none",
            },
            {
              y: 0,
              opacity: 1,
              display: "block",
              onComplete: () => {
                timeline.pause();
                setTimeout(() => {
                  //recursively call timeline after 5s
                  timeline.play(); 
                }, 5000);
              },
            })
            timeline.set(bulletPointRefs.current[0], {borderColor: "#AA4586",});
            timeline.set(bulletPointRefs.current[2], {borderColor: "#9d9d9d",});

            timeline.to(imageRefs.current[2],{
              y:-200,
              opacity: 0,
              display: "none",
              onComplete: () => {
                runTimeline();
              }
            })
      };
    
      // Start the timeline for the first time
      runTimeline();

    
  }, []);

  
  return (
    <div className="w-full sm:w-[450px] flex items-center justify-between relative">
      <div
        id="bulletPoint"
        className="w-[50px] h-[50px] hidden sm:block rounded-full border-8 border-[#9d9d9d] z-[99]"
        ref={(element) => (bulletPointRefs.current[index] = element)}
        style={{marginTop: index == lastBulletPoint ? -26 : 0}}
      ></div>
      <div className="flex flex-col w-full mb-8 sm:max-w-[400px]">
        <h1 className="text-white pl-4 text-[1.2em] sm:text-[1.5em] mb-[10px]">{title}</h1>
        <p className=" ml-4 text-white text-[.9em] sm:text[1em]">{content}</p>
      </div>
      {index !==  lastBulletPoint ? (
        <div className="hidden sm:block">
          <div
            ref={(element) => (lineRefs.current[index] = element)}
            className="bg-tertiary w-[4px] h-[0px] position z-[2]"
          ></div>
          <div className="line-gradient position"></div>
        </div>
      ) : (
        <div>
          
        </div>
      )}
    </div>
  );
};

const Usp2 = () => {
  const imageRefs = useRef([]);

  return (
    <div className="w-full flex flex-col-reverse md:flex-row py-10 md:h-[90vh] justify-around items-center relative ">
      <div className="relative">
        <div className="bg-secondary w-[300px] rounded-full h-[500px] flex items-end">
          <div className="bg-[#B0A79F] h-[250px] w-full rounded-b-full flex items-end">
            <div className="bg-[#9F9797] h-[140px] w-full rounded-b-full"></div>
          </div>
        </div>
        <div className=" absolute top-[35%] left-[5%]">
          <img src={laptop} alt="laptop" ref={(element) => (imageRefs.current[0] = element)} className="w-[300px] " />
          <img src={heart_pc} alt="heart_pc" ref={(element) => (imageRefs.current[1]) = element} className="w-[300px] hidden"/>
          <img src={htmlJsCss} alt="htmlJsCssLogos" ref={(element) => (imageRefs.current[2]) = element} className="w-[250px] ml-4 hidden" />
        </div>
      </div>
      <div className="flex flex-col mb-8 md:mb-0">
        {bulletPoints.map((bulletPoint, index) => (
          <BulletPoint key={index} index={index} imageRefs={imageRefs} {...bulletPoint} />
        ))}
      </div>
    </div>
  );
};
export default Hoc(Usp2);
