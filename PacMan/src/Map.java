import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Map {

    BufferedImage mapaPng;

    ///MAKE IT IN RUN AND STOP OTHERS UNTIL ITS DONE
//    @SafeVarargs
//    Map(String url, ArrayList<? extends ObjCreator>...listy){
//
//        m=new ArrayList[listy.length];
//        try {
//            mapaPng = ImageIO.read(new File(url));
//        }catch (IOException e){}
//        for(int y =0;y<mapaPng.getHeight();y++){
//            for(int x=0;x<mapaPng.getWidth();x++){
//                for (ArrayList<? extends ObjCreator> objCreatorslist : listy) {
//                    for ( int z =0;z<objCreatorslist.size(); z++) {
//                        if(objCreatorslist.get(z).mapIdColor.compareTo(new ColorRGB(mapaPng.getRGB(x,y)))){
//
//                        }
//                    }
//                    System.out.println();
//                }
//            }
//        }
//    }
//=====================//
    Map(String url,ArrayList<Blok> bloki,ArrayList<Blok> blokiNO1,Hero hero){


        try {
            mapaPng = ImageIO.read(new File(url));
        }catch (IOException e){}

        for(int y =0;y<mapaPng.getHeight();y++){
            for(int x=0;x<mapaPng.getWidth();x++){

                if(hero.mapIdColor.compareTo(new ColorRGB( mapaPng.getRGB(x,y)))){
                    hero.setPosition(x*32,y*32);
                }
                for(Blok blok:bloki){
                    if(blok.mapIdColor.compareTo(new ColorRGB( mapaPng.getRGB(x,y)))) {
                        Blok bTe = new Blok(blok.url, blok.mapIdColor);
                        bTe.setPosition(x*32,y*32);
                        blokiNO1.add(bTe);
                    }
                }
                //for(){}
            }
        }
    }
}
