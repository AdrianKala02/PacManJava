import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Map {

    BufferedImage mapaPng;
    private Character[][] mapReturn;
    public Character[][] mapReturn(){
        return mapReturn;
    }

    Map(String url,ArrayList<Blok> bloki,Hero hero){
        try {
            mapaPng = ImageIO.read(new File(url));
        }catch (IOException e){}

        mapReturn=new Character[mapaPng.getWidth()][mapaPng.getHeight()];

        for(int y =0;y<mapaPng.getHeight();y++){
            for(int x=0;x<mapaPng.getWidth();x++){
                mapReturn[x][y]='X';
            }
        }

        for(int y =0;y<mapaPng.getHeight();y++){
            for(int x=0;x<mapaPng.getWidth();x++){

                if(hero.mapIdColor.compareTo(new ColorRGB( mapaPng.getRGB(x,y)))){
                    mapReturn[x][y]=hero.getIdChar();
                }
                for(Blok blok:bloki){
                    if(blok.mapIdColor.compareTo(new ColorRGB( mapaPng.getRGB(x,y)))) {
                        mapReturn[x][y]=blok.getIdChar();
                    }
                }

            }
        }
    }
}
