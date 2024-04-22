import { useEffect, useRef, useState } from "react";
import { Hoc } from "./HOC";
import { SplitText } from "gsap-trial/SplitText";
import { ScrollTrigger } from "gsap/ScrollTrigger.js";
import { gsap } from "gsap/gsap-core";
import useWindowDimensions from "../utils/useWindowDemensions.js";
import emailjs from "@emailjs/browser";

const CTA = () => {
  const formRef = useRef();
  const [form, setForm] = useState({
    email: "",
    naam: "",
    nummer: "",
    budget: "",
    overig: "",
  });
  const [loading, setLoading] = useState(false);

  const handleChange = (e) => {
    const { target } = e;
    const { name, value } = target;

    setForm({
      ...form,
      [name]: value,
    });
  };
  const isValidForm = () => {
    // Check if name exists
    if (!form.naam) return false;

    // Check if number is a valid Dutch number
    // This is a very basic check, you may want to use a more comprehensive validation
    if (!/^(\+31|0)[1-9][0-9]{8}$/.test(form.nummer)) return false;

    // Check if email is valid
    // This is a very basic check, you may want to use a more comprehensive validation
    if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(form.email)) return false;

    // Check if budget is filled
    if (!/^\d+$/.test(form.budget)) return false;

    return true;
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    setLoading(true);

    emailjs
      .send(
        "service_thr6p79",
        "template_yt3789i",
        {
          from_name: form.naam,
          to_name: "Joris Mioulet",
          from_email: form.email,
          to_email: "jmioulet@gmail.com",
          message: `Budget: ${form.budget} \n Email: ${form.email} \n Nummer: ${form.nummer} \n Overig: ${form.overig}`,
        },
        "qIdWhb-c4SEZbru20"
      )
      .then(
        () => {
          setLoading(false);
          alert("Bedankt voor de reactie! Ik zal zo snel mogelijk reageren.");

          setForm({
            naam: "",
            email: "",
            overig: "",
            budget: "",
            nummer: "",
          });
        },
        (error) => {
          setLoading(false);
          console.error(error);

          alert("Ahh, er ging iets mis. Probeer alsjeblieft opnieuw.");
        }
      );
  };
  const innerDivBackground = {
    backgroundColor: "rgba(1, 22, 30, .95)",
  };
  const inputStyle = {
    backgroundColor: "rgba(77, 77, 77, .8)",
    borderColor: "rgb(77, 77, 77)",
    color: "white",
  };
  const headerStyle = {
    transition: "transform .5s",
    clipPath: "polygon(0 0, 100% 0%, 100% 100%, 0% 100%)",
    lineHeight: "5.9",
  };
  const headerRef = useRef(null);
  const divRef = useRef(null);

  const { height, width } = useWindowDimensions();
  useEffect(() => {
    gsap.registerPlugin(ScrollTrigger);
    gsap.registerPlugin(SplitText);

    const mySplitText = new SplitText(headerRef.current, {
      type: "chars",
    });
    gsap.fromTo(
      mySplitText.chars,
      {
        y: 115,
      },
      {
        y: 0,
        stagger: 0.05,
        delay: 0.2,
        duration: 0.1,
        scrollTrigger: {
          trigger: divRef.current,
        },
      }
    );
  }, []);

  return (
    <div className="w-full items-center flex flex-col">
      <h1 ref={headerRef} className=" z-[10] mb-[-100px]" style={headerStyle}>
        Kom in{" "}
        <span className="text-tertiary pulsating-text mx-[5px]">Contact</span>
      </h1>
      <form ref={formRef} onSubmit={handleSubmit}>
        <div
          className="w-full flex items-center justify-center py-20"
          ref={divRef}
        >
          <div
            className=" w-[90%] flex flex-col items-center justify-center p-10"
            style={innerDivBackground}
          >
            <div className="p-10 flex items-center justify-center flex-col md:flex-row">
              <input
                type="text"
                value={form.naam}
                name="naam"
                className="border-4 md:mr-12 px-6 py-2"
                placeholder="Naam"
                style={inputStyle}
                onChange={handleChange}
              />
              <input
                type="text"
                value={form.nummer}
                name="nummer"
                className="border-4 px-6 py-2 mt-8 md:mt-0"
                placeholder="Nummer"
                style={inputStyle}
                onChange={handleChange}
              />
            </div>
            <div className="px-10 pb-10 flex items-center justify-center flex-col md:flex-row">
              <input
                type="email"
                value={form.email}
                name="email"
                className="border-4 md:mr-12 px-6 py-2"
                placeholder="Email"
                style={inputStyle}
                onChange={handleChange}
              />
              <input
                type="text"
                value={form.budget}
                name="budget"
                className="border-4 px-6 py-2 mt-8 md:mt-0"
                placeholder="Budget"
                style={inputStyle}
                onChange={handleChange}
              />
            </div>
            <div>
              <textarea
                placeholder="Overige informatie (optioneel)"
                onChange={handleChange}
                value={form.overig}
                name="overig"
                rows="8"
                cols={width > 600 ? "50" : `${width / 10 - 10}`}
                className="border-4 p-4"
                style={inputStyle}
              />
            </div>
            <button
              type="submit"
              className="text-[2rem] bg-quartiary text-white px-14 py-4 rounded-full mt-10 disabled:opacity-20"
              disabled={!isValidForm()}
            >
              {loading ? "versturen..." : "verstuur"}
            </button>
          </div>
        </div>
      </form>
    </div>
  );
};
export default Hoc(CTA);
