import React from 'react'
import { ShaderGradientCanvas, ShaderGradient } from 'shadergradient'
import * as reactSpring from '@react-spring/three'
import * as drei from '@react-three/drei'
import * as fiber from '@react-three/fiber'
import { useThree, useFrame  } from '@react-three/fiber'
function Gradient() {
  const { scene } = useThree()

  useFrame(() => {
    const mesh = scene.getObjectByName('shadergradient-mesh')

    if (mesh.material.userData.uNoiseStrength) {
      mesh.material.userData.uNoiseStrength.value = 10
    }
  })

  return (
    <ShaderGradient
      control='query'
      urlString='https://www.shadergradient.co/customize?animate=on&axesHelper=off&bgColor1=%23000000&bgColor2=%23000000&brightness=1&cAzimuthAngle=180&cDistance=3.6&cPolarAngle=90&cameraZoom=1&color1=%23AA4586&color2=%2345AA69&color3=%2301161E&destination=onCanvas&embedMode=off&envPreset=city&format=gif&fov=45&frameRate=10&gizmoHelper=hide&grain=on&lightType=3d&pixelDensity=1.5&positionX=-1.4&positionY=0&positionZ=0&range=enabled&rangeEnd=40&rangeStart=0&reflection=0.1&rotationX=0&rotationY=10&rotationZ=50&shader=positionMix&type=plane&uAmplitude=0&uDensity=0.9&uFrequency=5.5&uSpeed=0.2&uStrength=3.9&uTime=0&wireframe=false'
    />
  )
}


function MyShaderGradient() {
  return (
    <ShaderGradientCanvas
      importedFiber={{ ...fiber, ...drei, ...reactSpring }}
      style={{
        position: 'absolute',
        top: 80,
        zIndex: -1,
        opacity: .2
      }}
    >
      <Gradient />
    </ShaderGradientCanvas>
  )
}

export default MyShaderGradient