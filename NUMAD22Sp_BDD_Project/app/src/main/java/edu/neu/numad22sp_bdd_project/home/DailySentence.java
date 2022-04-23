package edu.neu.numad22sp_bdd_project.home;

import java.util.ArrayList;

import edu.neu.numad22sp_bdd_project.R;

public class DailySentence {
    private ArrayList<MyImage> image = new ArrayList<>();

    public DailySentence(){
        image.add(new MyImage(R.drawable.image1));
        image.add(new MyImage(R.drawable.image2));
        image.add(new MyImage(R.drawable.image3));
        image.add(new MyImage(R.drawable.image4));
        image.add(new MyImage(R.drawable.image5));
        image.add(new MyImage(R.drawable.image6));
        image.add(new MyImage(R.drawable.image7));
        image.add(new MyImage(R.drawable.image8));
        image.add(new MyImage(R.drawable.image9));
        image.add(new MyImage(R.drawable.image10));
        image.add(new MyImage(R.drawable.image11));
        image.add(new MyImage(R.drawable.image12));
        image.add(new MyImage(R.drawable.image13));
        image.add(new MyImage(R.drawable.image14));
        image.add(new MyImage(R.drawable.image15));
        image.add(new MyImage(R.drawable.image16));
        image.add(new MyImage(R.drawable.image17));
        image.add(new MyImage(R.drawable.image18));
        image.add(new MyImage(R.drawable.image19));
        image.add(new MyImage(R.drawable.image20));
        image.add(new MyImage(R.drawable.image21));
        image.add(new MyImage(R.drawable.image22));
        image.add(new MyImage(R.drawable.image23));
        image.add(new MyImage(R.drawable.image24));
        image.add(new MyImage(R.drawable.image25));
        image.add(new MyImage(R.drawable.image26));
        image.add(new MyImage(R.drawable.image27));
        image.add(new MyImage(R.drawable.image28));
        image.add(new MyImage(R.drawable.image29));
        image.add(new MyImage(R.drawable.image30));
        image.add(new MyImage(R.drawable.image31));
        image.add(new MyImage(R.drawable.image32));
        image.add(new MyImage(R.drawable.image33));
        image.add(new MyImage(R.drawable.image34));
        image.add(new MyImage(R.drawable.image35));
        image.add(new MyImage(R.drawable.image36));
        image.add(new MyImage(R.drawable.image37));
        image.add(new MyImage(R.drawable.image38));
        image.add(new MyImage(R.drawable.image39));
        image.add(new MyImage(R.drawable.image40));
        image.add(new MyImage(R.drawable.image41));
        image.add(new MyImage(R.drawable.image42));
        image.add(new MyImage(R.drawable.image43));
        image.add(new MyImage(R.drawable.image44));
        image.add(new MyImage(R.drawable.image45));
        image.add(new MyImage(R.drawable.image46));
        image.add(new MyImage(R.drawable.image47));
        image.add(new MyImage(R.drawable.image48));
        image.add(new MyImage(R.drawable.image49));
        image.add(new MyImage(R.drawable.image50));
        image.add(new MyImage(R.drawable.image51));
        image.add(new MyImage(R.drawable.image52));
        image.add(new MyImage(R.drawable.image53));
        image.add(new MyImage(R.drawable.image54));
        image.add(new MyImage(R.drawable.image55));
        image.add(new MyImage(R.drawable.image56));
        image.add(new MyImage(R.drawable.image57));
        image.add(new MyImage(R.drawable.image58));
        image.add(new MyImage(R.drawable.image59));
        image.add(new MyImage(R.drawable.image60));

    }

    public int getImage(int num){
        return image.get(num).getImage();
    }
}
