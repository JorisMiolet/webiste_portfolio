import { useState } from "react"
const FooterSection = ({onFooterClick}) => {

    return (
        <>
            <div className="w-full">
                <div className="main-div bg-primary md:h-[90vh] w-full py-40 px-20 lg:px-60  flex flex-col md:flex-row justify-between">
                    <div>
                        <h1 className="text-tertiary">JM WebSolutions</h1>
                        <ul >
                            <li>
                                <p onClick={() => onFooterClick('portfolio')}  className="text-[#bfbbbb] leading-[4] hover:text-tertiary text-[2rem] cursor-pointer">Portfolio</p>
                            </li>
                            <li>
                                <p onClick={() => onFooterClick('about')} className="text-[#bfbbbb] leading-[4] hover:text-tertiary text-[2rem] cursor-pointer">About</p>
                            </li>
                            
                            <li>
                                <p onClick={() => onFooterClick('cta')} className="text-[#bfbbbb] leading-[4] hover:text-tertiary text-[2rem] cursor-pointer">Contact</p>
                            </li>
                        </ul>
                    </div>
                    <div>
                        <h1 className="text-tertiary">Socials</h1>
                        <ul>
                            <li className="py-10 footer-svg">
                                <a href="" target="_blank"><svg xmlns="http://www.w3.org/2000/svg" width="90" fill="white" height="90" viewBox="0 0 24 24"><path d="M12 2.163c3.204 0 3.584.012 4.85.07 3.252.148 4.771 1.691 4.919 4.919.058 1.265.069 1.645.069 4.849 0 3.205-.012 3.584-.069 4.849-.149 3.225-1.664 4.771-4.919 4.919-1.266.058-1.644.07-4.85.07-3.204 0-3.584-.012-4.849-.07-3.26-.149-4.771-1.699-4.919-4.92-.058-1.265-.07-1.644-.07-4.849 0-3.204.013-3.583.07-4.849.149-3.227 1.664-4.771 4.919-4.919 1.266-.057 1.645-.069 4.849-.069zm0-2.163c-3.259 0-3.667.014-4.947.072-4.358.2-6.78 2.618-6.98 6.98-.059 1.281-.073 1.689-.073 4.948 0 3.259.014 3.668.072 4.948.2 4.358 2.618 6.78 6.98 6.98 1.281.058 1.689.072 4.948.072 3.259 0 3.668-.014 4.948-.072 4.354-.2 6.782-2.618 6.979-6.98.059-1.28.073-1.689.073-4.948 0-3.259-.014-3.667-.072-4.947-.196-4.354-2.617-6.78-6.979-6.98-1.281-.059-1.69-.073-4.949-.073zm0 5.838c-3.403 0-6.162 2.759-6.162 6.162s2.759 6.163 6.162 6.163 6.162-2.759 6.162-6.163c0-3.403-2.759-6.162-6.162-6.162zm0 10.162c-2.209 0-4-1.79-4-4 0-2.209 1.791-4 4-4s4 1.791 4 4c0 2.21-1.791 4-4 4zm6.406-11.845c-.796 0-1.441.645-1.441 1.44s.645 1.44 1.441 1.44c.795 0 1.439-.645 1.439-1.44s-.644-1.44-1.439-1.44z"/></svg></a>
                            </li>
                            <li className="py-10 footer-svg">
                            <a href="https://www.linkedin.com/in/joris-mioulet-524a7724a/" target="_blank"> <svg xmlns="http://www.w3.org/2000/svg" width="90" fill="white" height="90" viewBox="0 0 24 24"><path d="M19 0h-14c-2.761 0-5 2.239-5 5v14c0 2.761 2.239 5 5 5h14c2.762 0 5-2.239 5-5v-14c0-2.761-2.238-5-5-5zm-11 19h-3v-11h3v11zm-1.5-12.268c-.966 0-1.75-.79-1.75-1.764s.784-1.764 1.75-1.764 1.75.79 1.75 1.764-.783 1.764-1.75 1.764zm13.5 12.268h-3v-5.604c0-3.368-4-3.113-4 0v5.604h-3v-11h3v1.765c1.396-2.586 7-2.777 7 2.476v6.759z"/></svg></a>
                            </li>
                            <li className="py-10 footer-svg">
                            <a href="" target="_blank"><svg xmlns="http://www.w3.org/2000/svg" width="90" fill="white" height="90" viewBox="0 0 24 24"><path d="M24 4.557c-.883.392-1.832.656-2.828.775 1.017-.609 1.798-1.574 2.165-2.724-.951.564-2.005.974-3.127 1.195-.897-.957-2.178-1.555-3.594-1.555-3.179 0-5.515 2.966-4.797 6.045-4.091-.205-7.719-2.165-10.148-5.144-1.29 2.213-.669 5.108 1.523 6.574-.806-.026-1.566-.247-2.229-.616-.054 2.281 1.581 4.415 3.949 4.89-.693.188-1.452.232-2.224.084.626 1.956 2.444 3.379 4.6 3.419-2.07 1.623-4.678 2.348-7.29 2.04 2.179 1.397 4.768 2.212 7.548 2.212 9.142 0 14.307-7.721 13.995-14.646.962-.695 1.797-1.562 2.457-2.549z"/></svg></a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div className="bg-primary w-full h-10 border-t-4 border-tertiary flex px-8">
                    <div className="flex flex-1">
                        <p className="text-white cursor-pointer">privacy</p>
                        <p className="text-white ml-8 cursor-pointer">terms</p>
                    </div>
                    <div>
                        <p className="text-white cursor-pointer">Design en development by Joris Mioulet 2024 &copy;</p>
                    </div>
                </div>
            </div>
            <style jsx>{`
                @media (max-width:1120px) {
                    .main-div{
                        padding: 100px 100px;
                    }
                }
                @media (max-width:661px) {
                    
                    .main-div p {
                        font-size: 1.5em;
                    }
                    .main-div h1 {
                        font-size: 2.3em;
                    }
                    .main-div svg{
                        width: 70px;
                    }
                }
                @media (max-width:550px) {
                    
                    .main-div p {
                        font-size: 1em;
                    }
                    .main-div h1 {
                        font-size: 1.5em;
                    }
                    .main-div svg{
                        width: 50px;
                    }
                    .footer
                }
            `}</style>
        </>
    )
}
export default FooterSection