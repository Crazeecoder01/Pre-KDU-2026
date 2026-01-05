import { useState, useEffect } from "react";

interface CountdownTimerProps {
  seconds: number;
  onComplete?: () => void;
}

export default function CountdownTimer({ seconds, onComplete }: CountdownTimerProps) {
  const [timeLeft, setTimeLeft] = useState(seconds);

  useEffect(() => {
    if (timeLeft <= 0) {
      onComplete?.();
      return;
    }

    const timer = setTimeout(() => {
      setTimeLeft(timeLeft - 1);
    }, 1000);

    return () => clearTimeout(timer);
  }, [timeLeft, onComplete]);

  return (
    <div className="countdown-timer">
      <span>{timeLeft}s</span>
    </div>
  );
}
