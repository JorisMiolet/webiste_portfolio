import React, { useEffect } from 'react';
import lottie from 'lottie-web';
import { smiling_man } from '../../assets/images';

const Smile = () => {
    useEffect(() => {
        const smileContainer = document.getElementById('smile');
        
          const animation = lottie.loadAnimation({
            container: smileContainer,
            renderer: 'svg',
            loop: true,
            autoplay: true,
            animationData: smiling_man, 
            rendererSettings: {
                preserveAspectRatio: "xMidYMid slice",
                  },
          });
        
        return () => animation.destroy();
      }, [])
    return (
    <div className='w-[200px]' id='smile'>

    </div>
      )
}
export default Smile