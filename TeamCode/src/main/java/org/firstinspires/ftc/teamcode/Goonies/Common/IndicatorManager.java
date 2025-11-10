package org.firstinspires.ftc.teamcode.Goonies.Common;

import java.util.ArrayList;

public class IndicatorManager {
    public ArrayList<IIndicator> indicators;

    public IndicatorManager()
    {
        indicators = new ArrayList<IIndicator>();
    }

    public void addIndicator(IIndicator indicator) {
        indicators.add(indicator);
    }

    public void update(RobotState robotState)
    {
        for (IIndicator indicator : indicators)
        {
            indicator.Update(robotState);
        }
    }
}
