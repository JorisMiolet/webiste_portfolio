import { useRef, useState } from "react";
import { contact, about } from "../assets/images";
import HamburgerMenu from "./HamburgerMenu";
const Navbar = ({onNavClick}) =>{
    const [hamburgerOpen, setHamburgerOpen] = useState(false)
    const toggleHamburger = () => {
        setHamburgerOpen(!hamburgerOpen);
    }
    const closeBurger = () => {
        if(hamburgerOpen)setHamburgerOpen(!hamburgerOpen)
    }
    return(
        <>
            <div className="bg-primary py-5 glow flex px-[2rem] main-wrapper">
                <div className="mr-0 sm:mr-40">
                    <h1 className="text-white text-[2.5rem]">JM WebSolutions</h1>
                </div>
                <div className="flex flex-1 nav-wrapper">
                    <ul className="justify-between mx-10 flex w-full">
                        <li>
                            <div className="flex items-center">
                                <img src={contact} alt="Contact Icon" className="w-10 h-10 mr-2 mb-2" />
                                <h2 className="text-white text-[2.5rem] list-hover" onClick={() => { onNavClick('cta'); closeBurger()}}>Contact</h2>
                            </div>
                        </li>
                        <li>
                            <div className="flex items-center">
                                <img src={about} alt="About Icon" className="w-10 h-10 mr-2 mb-2" />
                                <h2 className="text-white text-[2.5rem] list-hover" onClick={() => {onNavClick('about') ; closeBurger()}}>About</h2>
                            </div>
                        </li>
                        <li>
                            <div className="flex items-center">
                                <svg className="mr-2 mb-3" xmlns="http://www.w3.org/2000/svg" width="30" height="30" viewBox="0 0 24 24" fill="none" stroke="white" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-help-circle"><circle cx="12" cy="12" r="10"></circle><path d="M9.09 9a3 3 0 0 1 5.83 1c0 2-3 3-3 3"></path><line x1="12" y1="17" x2="12.01" y2="17"></line></svg>
                                <h2 className="text-white text-[2.5rem] list-hover" onClick={() => {onNavClick('usp'); closeBurger()}}>Waarom ik?</h2>
                            </div>
                        </li>
                    </ul>
                </div>
                <div className="hamburger" onClick={toggleHamburger}>
                    <HamburgerMenu />
                </div>
            </div>
            <style jsx>{`
                .hamburger{
                    display: none;
                }
                @media ( max-width: 1150px ){

                    .hamburger{
                        display: flex;
                        position: ${hamburgerOpen ? 'fixed' : 'absolute'};
                        top: ${hamburgerOpen ? '20px' : '10px'};
                        right: 10px;
                        z-index: 10;
                    }

                    .nav-wrapper ul{
                        display: ${hamburgerOpen ? 'flex' : 'none'};
                        background-color: #01161E;
                        height: 100vh;
                        width: 100vw;
                        top: 50px;
                        left: 0;
                        margin: 0;
                        flex-direction: column;
                        align-items: center;
                        justify-content: space-around;
                        position: fixed;
                        z-index: 999;
                    }
                    .main-wrapper{
                         ${hamburgerOpen ? 'position: fixed;': ''}
                         ${hamburgerOpen ? 'left: 0;': ''}
                         ${hamburgerOpen ? 'top: 0;': ''}
                         ${hamburgerOpen ? 'width: 100vw;': ''}
                         ${hamburgerOpen ? 'z-index: 999;': ''}
                    }
                    .main-wrapper h1{
                        font-size: 2rem;
                   }
                }
            `}</style>
        </>
    )
}
export default Navbar;