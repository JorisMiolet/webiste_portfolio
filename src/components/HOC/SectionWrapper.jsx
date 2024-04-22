import gsap from 'gsap';
import { useEffect, useRef } from 'react';
import { ScrollTrigger } from 'gsap/all';

const Hoc = (Component) => 
    function HOC() {
        const componentRef = useRef(null)
        useEffect(() => {
            gsap.registerPlugin(ScrollTrigger)
            gsap.from(componentRef.current , {
                y: -200,
                opacity: 0,
                // scrollTrigger: {
                //     trigger: componentRef.current
                // }
            })
        },[])
        return (
            <>
                {/* <div className='top-gradient pt-[50px]'></div> */}
                <div className='bottom-gradient'></div>
                <div className="w-full flex justify-center bg-primary pt-20">
                    <Component/>
                </div>
                <div className='bottom-top-gradient'></div>
                {/* <div className='bottom-bottom-gradient'></div> */}
                <style jsx>{`
                .top-gradient{
                    width: 100%;
                    height: 100px;
                    background: rgb(8,0,26);
                    background: linear-gradient(180deg, rgba(8,0,26,1) 45%, rgba(170,69,134,1) 100%);
                }
                .bottom-bottom-gradient{
                    width: 100%;
                    height: 100px;
                    background: rgb(170,69,134);
                    background: linear-gradient(180deg, rgba(170,69,134,1) 0%, rgba(8,0,26,1) 45%);
                }
                .bottom-gradient{
                    width: 100%;
                    height: 200px;
                    background: rgb(170,69,134);
                    background: linear-gradient(180deg, rgba(170,69,134,1) 0%, rgba(1,22,30,1) 100%);
                }
                .bottom-top-gradient{
                    width: 100%;
                    height: 200px;
                    background: rgb(1,22,30);
                    background: linear-gradient(180deg, rgba(1,22,30,1) 0%, rgba(170,69,134,1) 100%);
                }
                `}</style>
            </>
        )
    }
    export default Hoc;