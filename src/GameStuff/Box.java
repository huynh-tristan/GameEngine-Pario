package GameStuff;

public class Box {
    public float orientation;
    public float[] tr, tl, br, bl;
    public float width;
    public float height;
    public float[] centerPosition;

    public Box(float[] tr, float[] tl, float[] br, float[] bl, float orientationAngle, float width, float height, float[] center) {
        this.tr = tr;
        this.tl = tl;
        this.br = br;
        this.bl = bl;
        this.orientation = orientationAngle;
        this.width = width;
        this.height = height;
        this.centerPosition = center;
    }

    public void updateBoxCoordinates(float[] tr, float[] tl, float[] br, float[] bl, float[] centerPosition) {
        this.tr = tr;
        this.tl = tl;
        this.br = br;
        this.bl = bl;
        this.centerPosition = centerPosition;
    }

    public void updateBoxOrientation(float angle) {
        this.orientation = angle;
    }

    public float[] calculateClosestPoint(float[] circleCenter) {
        float[] axis1 = new float[]{(float) Math.cos(Math.toRadians(orientation)), (float) Math.sin(Math.toRadians(orientation))};
        float[] axis2 = new float[]{(float) Math.sin(Math.toRadians(orientation)), (float) -Math.cos(Math.toRadians(orientation))};

        float[] differenceInCenters = new float[]{circleCenter[0] - centerPosition[0], circleCenter[1] - centerPosition[1]};
        float xDot = HelperFunctions.DotProduct(differenceInCenters, axis1);
        float yDot = HelperFunctions.DotProduct(differenceInCenters, axis2);

        float xConverted = HelperFunctions.Clamp(xDot, -width / 2, width / 2);
        float yConverted = HelperFunctions.Clamp(yDot, -height / 2, height / 2);

        if (xDot == xConverted && yDot == yConverted) {
            return circleCenter;
        } else {
            float[] resultingPoint = centerPosition;
            resultingPoint[0] += axis1[0] * xConverted + axis2[0] * yConverted;
            resultingPoint[1] += axis1[1] * xConverted + axis2[1] * yConverted;
            return resultingPoint;
        }
    }
}
