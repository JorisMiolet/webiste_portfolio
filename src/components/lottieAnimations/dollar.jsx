import React, { useEffect } from 'react';
import lottie from 'lottie-web';
import { dollar} from '../../assets/images';

const Dollar = () => {
    useEffect(() => {
        const dollarContainer = document.getElementById('dollar');
        
          const animation = lottie.loadAnimation({
            container: dollarContainer,
            renderer: 'svg',
            loop: true,
            autoplay: true,
            animationData: dollar, 
            rendererSettings: {
                preserveAspectRatio: "xMidYMid slice",
                  },
          });
        
        return () => animation.destroy();
      }, [])
    return (
    <div className='w-[200px]' id='dollar'>

    </div>
      )
}
export default Dollar