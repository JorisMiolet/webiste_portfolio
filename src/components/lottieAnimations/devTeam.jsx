import React, { useEffect } from 'react';
import lottie from 'lottie-web';
import { devTeam} from '../../assets/images';

const DevTeam = () => {
    useEffect(() => {
        const devTeamContainer = document.getElementById('devTeam');
        
          const animation = lottie.loadAnimation({
            container: devTeamContainer,
            renderer: 'svg',
            loop: true,
            autoplay: true,
            animationData: devTeam, 
            rendererSettings: {
                preserveAspectRatio: "xMidYMid slice",
                  },
          });
        
        return () => animation.destroy();
      }, [])
    return (
    <div className='w-[200px]' id='devTeam'>

    </div>
      )
}
export default DevTeam