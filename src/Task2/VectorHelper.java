import java.util.Random;

public class VectorHelper {

    private double x,y,z;

    public VectorHelper(double x,double y,double z){

        this.x = x;
        this.y = y;
        this.z = z;

    }

    public double vectorLen(){
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
    }

    public double scalarMulti(VectorHelper vector){
        return this.x*vector.x + this.y*vector.y + this.z*vector.z;
    }

    public VectorHelper vectorMulti(VectorHelper vector){
        double newX = this.y * vector.z - this.z * vector.y;
        double newY = this.z * vector.x - this.x * vector.z;
        double newZ = this.x * vector.y - this.y * vector.x;
        return new VectorHelper(newX, newY, newZ);
    }

    public double vectorCosinus(VectorHelper vector){

        double dotProduct = this.scalarMulti(vector);
        double lengthProduct = this.vectorLen() * vector.vectorLen();

        // Проверка деления на ноль
        if (lengthProduct == 0) {
            return 0;
        }
        return dotProduct / lengthProduct;
    }

    public VectorHelper vectorSum(VectorHelper vector){
        return new VectorHelper(this.x + vector.x , this.y + vector.y, this.z + vector.z);
    }

    public VectorHelper vectorDiff(VectorHelper vector){
        return new VectorHelper(this.x - vector.x , this.y - vector.y, this.z - vector.z);
    }

    public static VectorHelper[] generateRandomVectors(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N должно быть положительным числом");
        }

        Random random = new Random();
        VectorHelper[] vectors = new VectorHelper[N];

        for (int i = 0; i < N; i++) {
            double x = random.nextDouble() ;
            double y = random.nextDouble() ;
            double z = random.nextDouble() ;
            vectors[i] = new VectorHelper(x, y, z);
        }

        return vectors;
    }


}
