import React, { Suspense, useEffect, useState } from "react";
import { Canvas} from "@react-three/fiber";
import { OrbitControls, Preload, useGLTF } from "@react-three/drei";

import CanvasLoader from "../Loader";

const Logo = ({ isMobile }) => {
  const { scene } = useGLTF("/logo/logo3.glb");

  return (
    <mesh>
      <hemisphereLight intensity={1} groundColor='white' />
      <directionalLight />    
      
      <pointLight intensity={1} />
      <primitive
        object={scene}
        scale={isMobile ? 0.7 : 1}
        position={isMobile ? [0, -3, -2.2] : [0, -3.25, -1.5]}
        rotation={[0, Math.PI / 2, 0]}
      />
    </mesh>
  );
};

const LogoCanvas = () => {
  const [isMobile, setIsMobile] = useState(false);

  useEffect(() => {
    // Add a listener for changes to the screen size
    const mediaQuery = window.matchMedia("(max-width: 500px)");

    // Set the initial value of the `isMobile` state variable
    setIsMobile(mediaQuery.matches);

    // Define a callback function to handle changes to the media query
    const handleMediaQueryChange = (event) => {
      setIsMobile(event.matches);
    };

    // Add the callback function as a listener for changes to the media query
    mediaQuery.addEventListener("change", handleMediaQueryChange);

    // Remove the listener when the component is unmounted
    return () => {
      mediaQuery.removeEventListener("change", handleMediaQueryChange);
    };
  }, []);

  return (
    <Canvas
      frameloop='demand'
      shadows
      dpr={[1, 2]}
      camera={{ position: [20, -12, -5], fov: 75 }}
      gl={{ preserveDrawingBuffer: true }}
      style={{ width: "100%", height: isMobile ? "30vh" : "70vh" }}
    >
      <Suspense fallback={<CanvasLoader />}>
        <OrbitControls
          enableZoom={false}
          maxPolarAngle={Math.PI / 2}
          minPolarAngle={Math.PI / 2}
          autoRotate
        />
        <Logo isMobile={isMobile} />
      </Suspense>

      <Preload all />
    </Canvas>
  );
};

export default LogoCanvas;