package gameState;

import core.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;

public class InfoScreen extends GameScreen {

    Image proggyGif, shop;
    BufferedImage beggar, unicef, shoes, voi, mask, proggyShoes, proggyVoi, proggyMask, proggy;
    public int page = 1;

    public InfoScreen(GamePanel gp) {
        this.gp = gp;
    }

    private void loadImg() {
        proggyGif = new ImageIcon(getClass().getResource("/graphics/Proggy.gif")).getImage();
        try {
            beggar = ImageIO.read(getClass().getResourceAsStream("/enemies/uteligger.png"));
            proggy = ImageIO.read(getClass().getResourceAsStream("/player1/Proggy_default1.png"));
            unicef = ImageIO.read(getClass().getResourceAsStream("/enemies/Unicef_right1.png"));
            shoes = ImageIO.read(getClass().getResourceAsStream("/graphics/goldshoes_powerup.png"));
            voi = ImageIO.read(getClass().getResourceAsStream("/graphics/voi.png"));
            mask = ImageIO.read(getClass().getResourceAsStream("/graphics/mask_powerup.png"));
            proggyMask = ImageIO.read(getClass().getResourceAsStream("/player1/Proggy_mask_default1.png"));
            proggyShoes = ImageIO.read(getClass().getResourceAsStream("/player1/Proggy_gold_default1.png"));
            proggyVoi = ImageIO.read(getClass().getResourceAsStream("/player1/Proggy_voi_default1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void draw(Graphics g) {
        loadImg();
        this.setGp(gp);
        this.setGraphics(g);
        
        String s1,s2, line1, line2, line3, line4, line5, line6, line7, line8, line9, line10, line11, line12, line13, line14, line15, line16, line17;
        
        if(gp.getLang() == Languages.NORWEGIAN) {
            //Text
        	s1 = "Tilbake";
        	s2 = "Neste";
            line1 = "Proggy Gonzales har lest hardt i hele år, men i dag hadde han sin siste eksamen!";
            line2 = "For å feire skal han på eksamensfest, og må rekke butikken før ølsalget stenger. Han har";
            line3 = "donert alle pengene sine til unicef, og må derfor løpe gjennom Bergen sentrum og samle";
            line4 = "penger for å få råd.";
            line5 = "";
            line6 = "I tillegg må han unngå Unicefarbeidere som ikke gir seg uansett hvor hardt han sier at han";
            line7 = "allerede er fadder, men det er ikke den eneste hindringen hans. Rundt om i Bergen er det";
            line8 = "også uteliggere som ber om penger. Proggy ikke har hjerte til å si nei når de spør, og må";
            line9 = "derfor holde seg unna de om han skal ha råd til øl.";
            line10 = "For å gjøre oppdraget lettere, finnes det flere ulike powerups i Bergen. Hver powerup har";
            line11 = "en tidsgrense på 10 sekunder. Det finnes sparkesykler som øker Proggys hastighet. Det";
            line12 = "koster 5kr fastpris + 1kr per sekund å kjøre en sparkesykkel, så han må passe på at han har";
            line13 = "nok penger igjen til å kjøpe øl etterpå. En annen måte Proggy kan øke hastigheten sin på er";
            line14 = "ved å ta på seg gullsko. De er noe vanskeligere å finne, men koster ingenting å bruke!";
            line15 = "Det er også noen masker som ligger rundt om i gatene. Når Proggy tar på en slik maske,";
            line16 = "blir han usynlig for uteliggere og Unicef-arbeidere, så han kan gå rett forbi dem uten å";
            line17 = "miste noe penger!";
        }
        else {
        	s1 = "Back";
        	s2 = "Next";
        	line1 = "Proggy Gonzales has been studying hard all year, but today he had his last exam!";
        	line2 = "To celebrate, he is going to a party, but he needs to get to the shop to buy beer before";
        	line3 = "closing time. He has donated all his money to Unicef, and therefore needs to collect all";
        	line4 = "the money he can find in the streets of Bergen. He needs to collect 100kr to be able to";
        	line5 = "afford enough beer.";
        	line6 = "It is important to avoid meeting Unicef charity workers with their collecting tins since";
        	line7 = "they won't leave him alone, even though he keeps telling them he already gives donations";
        	line8 = "regularly. There are also several beggars in the streets asking for money. Proggy cannot";
        	line9 = "say no when they ask him, so he has to avoid them if he's going to afford any beer.";
        	line10 = "To make things easier, there are luckily several power ups to be found in Bergen. Each";
        	line11 = "power up has a time limit of 10 seconds. There are scooters that boosts Proggy's speed";
            line12 = "and helps him save time. It costs 5kr to start a ride + 1 coin per second, so he has to";
            line13 = "make sure he has enough money to buy beer afterwards. Another way Proggy can boost his";
            line14 = "speed is by putting on gold shoes. They are a little bit harder to find, but won't cost";
            line15 = "Proggy anything to pick up! There are also a few masks lying on the street. When put on,";
            line16 = "Proggy becomes invisible for charity workers and homeless, so he can walk right past them";
            line17 = "without loosing any money!";

        }

        //Transparent background
        Color transparent = new Color(0, 0, 0, 0x80); // fourth argument is transparency - try out different values 0x00 - 0xff
        g.setColor(transparent);
        g.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        //White background
        g.setColor(Color.WHITE);
        int width = gp.tileSize*(gp.maxScreenCol - 4);
        int height = gp.tileSize*(gp.maxScreenRow - 4);

        int x = gp.screenWidth/2 - (width/2);
        int y = gp.screenHeight/2 - (height/2);

        g.fillRect(x, y, width, height);

        g.setColor(Color.BLACK);

        Font textFont = new Font("Monospaced", Font.PLAIN, 14);

        switch (page) {
            case 1:
                g.setFont(textFont);
                g.drawImage(proggy, x + gp.tileSize, y + gp.tileSize, 128, 128, null);
                g.drawString(line1, x + gp.tileSize/2, y + gp.tileSize + 175);
                g.drawString(line2, x + gp.tileSize/2, y + gp.tileSize + 200);
                g.drawString(line3, x + gp.tileSize/2, y + gp.tileSize + 225);
                g.drawString(line4, x + gp.tileSize/2, y + gp.tileSize + 250);
                g.drawString(line5, x + gp.tileSize/2, y + gp.tileSize + 275);
                g.drawImage(proggyGif, x + gp.tileSize*6, y + gp.tileSize + 275, 360, 94, null);

                //Back and next buttons
                this.draw(40);
                g.drawString(s1, x + gp.tileSize, y + height - 40);
                if (cmd == 1)
                    g.drawString(">", x + gp.tileSize/2, y + height - 40);

                g.drawString(s2, x + width-(gp.tileSize+32), y + height - 40);
                if (cmd == 2)
                    g.drawString(">", x + width-(2*gp.tileSize), y + height - 40);
                break;

            case 2:
                g.setFont(textFont);
                g.drawImage(beggar, x + gp.tileSize*3, y + gp.tileSize + 50, 128, 128, null);
                g.drawImage(unicef, x + gp.tileSize*7, y + gp.tileSize + 50, 128, 128, null);
                g.drawString(line6, x + gp.tileSize/2, y + gp.tileSize+250);
                g.drawString(line7, x + gp.tileSize/2, y + gp.tileSize+275);
                g.drawString(line8, x + gp.tileSize/2, y + gp.tileSize+300);
                g.drawString(line9, x + gp.tileSize/2, y + gp.tileSize+325);

                //Back and next buttons
                this.draw(40);
                g.drawString(s1, x + gp.tileSize, y + height - 40);
                if (cmd == 1)
                    g.drawString(">", x + gp.tileSize/2, y + height - 40);

                g.drawString(s2, x + width - (gp.tileSize + 32), y + height - 40);
                if (cmd == 2)
                    g.drawString(">", x + width - (2*gp.tileSize), y + height - 40);
                break;

            case 3:
                g.setFont(textFont);
                g.drawImage(shoes, x + gp.tileSize*2, y + gp.tileSize - 25, 91, 91, null);
                g.drawImage(voi, x + gp.tileSize*5, y + gp.tileSize - 30, 91, 91, null);
                g.drawImage(mask, x + gp.tileSize*8, y + gp.tileSize - 25, 91, 91, null);

                g.drawString(line10, x+gp.tileSize/2, y+gp.tileSize + 100);
                g.drawString(line11, x+gp.tileSize/2, y+gp.tileSize + 125);
                g.drawString(line12, x+gp.tileSize/2, y+gp.tileSize + 150);
                g.drawString(line13, x+gp.tileSize/2, y+gp.tileSize + 175);
                g.drawString(line14, x+gp.tileSize/2, y+gp.tileSize + 200);
                g.drawString(line15, x+gp.tileSize/2, y+gp.tileSize + 225);
                g.drawString(line16, x+gp.tileSize/2, y+gp.tileSize + 250);
                g.drawString(line17, x+gp.tileSize/2, y+gp.tileSize + 275);

                g.drawImage(proggyMask, x + gp.tileSize*8, y + gp.tileSize + 290, 91, 91, null);
                g.drawImage(proggyShoes, x + gp.tileSize*2 + 5, y + gp.tileSize + 290, 91, 91, null);
                g.drawImage(proggyVoi, x + gp.tileSize*5, y + gp.tileSize + 290, 91, 91, null);

                //Back and next buttons
                this.draw(40);
                g.drawString(s1, x + gp.tileSize, y + height - 40);
                if (cmd == 1)
                    g.drawString(">", x + gp.tileSize/2, y + height - 40);

        }
    }
}
