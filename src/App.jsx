import { Navbar, Hero, Portfolio, Usp, Usp2, About, CTA, FooterSection, ScrollToTopButton } from "./components"
import Toolbelt from "./components/toolbelt"
import { useRef } from "react"

const App = () =>{
  const ctaRef = useRef(null)
  const uspRef = useRef(null)
  const aboutRef = useRef(null)
  const portfolioRef = useRef(null)
  
  const scrollToTarget = (target) => {
    if (target === 'cta') {
      ctaRef.current.scrollIntoView({ behavior: 'smooth' });
    } else if (target === 'usp') {
      uspRef.current.scrollIntoView({ behavior: 'smooth' });
    } else if (target === 'about') {
      aboutRef.current.scrollIntoView({ behavior: 'smooth' });
    }else if (target === 'portfolio') {
      portfolioRef.current.scrollIntoView({ behavior: 'smooth' });
    }
  };

  return(
    <>
    <Navbar onNavClick={scrollToTarget} />
    <Hero OnButtonClick={scrollToTarget}/>
    <div ref={portfolioRef}>
      <Portfolio /> 
    </div>
    <div ref={uspRef}>
      <Usp />
    </div>
    <Usp2 />
    <div ref={aboutRef}>
      <About />
    </div>
    {/* <Toolbelt /> */}
    <div ref={ctaRef}>
      <CTA />
    </div>
    <FooterSection onFooterClick={scrollToTarget}/>
    <ScrollToTopButton />
    </>
  )
}

export default App
