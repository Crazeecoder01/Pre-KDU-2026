import { useState } from "react";
import CountdownTimer from "./CountdownTimer";

export default function MoviePremiumRentals(){
   const [isTimer1, setIsTimer1] = useState(false)
   const [isTimer2, setIsTimer2] = useState(false)
   const [isTimer3, setIsTimer3] = useState(false)
   const [isCnt1, setIsCnt1] = useState(false)
   const [isCnt2, setIsCnt2] = useState(false)
   const [isCnt3, setIsCnt3] = useState(false)
    function handleClick(seconds: any, timer:any){
        if(timer == 1)setIsTimer1(true);
        if(timer == 2)setIsTimer2(true);
        if(timer == 3)setIsTimer3(true);

        function tick() {

                if (seconds > 0) {
                    seconds--;
                    
                    setTimeout(tick, 1000);
                } else {
                    seconds = 0;
                }
            }

            tick();
    }
    
    function handleClick1(){
        setIsTimer1(false);
        setIsCnt1(true);
    }
    function handleClick2(){
        setIsTimer2(false);
        setIsCnt2(true);
    }
    function handleClick3(){
        setIsTimer3(false);
        setIsCnt3(true);
    }
    return(
        <div className="movie-list">
            <div >Movie 1
                {
                    isTimer1?(<CountdownTimer key={1} seconds={10} onComplete={() => setIsCnt1(true)}/>):(isCnt1?(<button className="btn-primary">Watch Now</button>):(<button className="btn-primary" onClick={()=>handleClick(10, 1)}>Countdown Start</button>))
                    
                }
                <button className="btn-primary" onClick={()=>handleClick1()}>Reset</button>
            </div>
            <div>Movie 2
                {
                    isTimer2?(<CountdownTimer key={2} seconds={10} onComplete={() => setIsCnt2(true)}/>):(isCnt2?(<button className="btn-primary">Watch Now</button>):(<button className="btn-primary" onClick={()=>handleClick(10, 2)}>Countdown Start</button>))
                }
                <button className="btn-primary" onClick={()=>handleClick2()}>Reset</button>
            </div>
            <div>Movie 3
                {
                    isTimer3?(<CountdownTimer key={3} seconds={10} onComplete={() => setIsCnt3(true)}/>):(isCnt3?(<button className="btn-primary">Watch Now</button>):(<button className="btn-primary" onClick={()=>handleClick(10, 3)}>Countdown Start</button>))
                }
                <button className="btn-primary" onClick={()=>handleClick3()}>Reset</button>
            </div>
            
        </div>
    )
}