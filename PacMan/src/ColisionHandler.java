public class ColisionHandler {

//   public static Hero hero;

//    ColisionHandler(Hero hero){
//            ColisionHandler.hero =hero;
//    }
//    public void objToboj(){
//        void ColisionHandler::objToboj(Hero hero, ObjCreator const& wall){
//            sf::FloatRect playerBounds= hero.shape.getGlobalBounds();
//            sf::FloatRect wallBounds= wall.shape.getGlobalBounds();
//            //bottom
//            if(playerBounds.top<wallBounds.top
//                    &&playerBounds.top+playerBounds.height<wallBounds.top+wallBounds.height
//                    && playerBounds.left<wallBounds.left+wallBounds.width
//                    &&playerBounds.left+playerBounds.width>wallBounds.left){
//                hero.velocity.y=0.f;
//                hero.shape.setPosition(playerBounds.left,wallBounds.top-playerBounds.height);
//            }
//            else if(playerBounds.top>wallBounds.top
//                    &&playerBounds.top+playerBounds.height>wallBounds.top+wallBounds.height
//                    && playerBounds.left<wallBounds.left+wallBounds.width
//                    &&playerBounds.left+playerBounds.width>wallBounds.left){
//                hero.velocity.y=0.f;
//                hero.shape.setPosition(playerBounds.left,wallBounds.top+wallBounds.height);
//            }
//            if(playerBounds.left<wallBounds.left
//                    &&playerBounds.left+playerBounds.width<wallBounds.left+wallBounds.width
//                    && playerBounds.top<wallBounds.top+wallBounds.height
//                    &&playerBounds.top+playerBounds.height>wallBounds.top){
//                hero.velocity.x=0.f;
//                hero.shape.setPosition(wallBounds.left-playerBounds.width, playerBounds.top);
//            }
//            //left
//            else if(playerBounds.left>wallBounds.left
//                    &&playerBounds.left+playerBounds.width>wallBounds.left+wallBounds.width
//                    && playerBounds.top<wallBounds.top+wallBounds.height
//                    &&playerBounds.top+playerBounds.height>wallBounds.top){
//                hero.velocity.x=0.f;
//                hero.shape.setPosition(wallBounds.left+wallBounds.width, playerBounds.top);
//            }
//        }
//    }

    public void colisionToWindow(Hero hero){

        if(hero.getPosX()>400){
            hero.setAclelerationX(0);
            hero.setPosX(hero.getPosX()-1);
        }
       else if(hero.getPosX()<0){
            hero.setAclelerationX(0);
            hero.setPosX(hero.getPosX()+1);
        }

        if(hero.getPosY()>400){
            hero.setAclelerationY(0);
            hero.setPosY(hero.getPosY()-1);
        }
        else if(hero.getPosY()<0){
            hero.setAclelerationY(0);
            hero.setPosY(hero.getPosY()+1);
        }
    }
    public  <T extends ObjCreator> void colisionObjToObj(Hero hero, T obj){
        FloatRect playerBounds= hero.shape.getGlobalBounds();
        FloatRect wallBounds= obj.sprite
            //bottom
            if(playerBounds.top<wallBounds.top
                    &&playerBounds.top+playerBounds.height<wallBounds.top+wallBounds.height
                    && playerBounds.left<wallBounds.left+wallBounds.width
                    &&playerBounds.left+playerBounds.width>wallBounds.left){
                hero.setAclelerationY(0);
                hero.shape.setPosition(playerBounds.left,wallBounds.top-playerBounds.height);
            }
   }
}
