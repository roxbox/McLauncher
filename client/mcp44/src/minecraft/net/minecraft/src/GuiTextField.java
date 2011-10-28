// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            Gui, GuiScreen, ChatAllowedCharacters, FontRenderer

public class GuiTextField extends Gui
{

    public GuiTextField(GuiScreen guiscreen, FontRenderer fontrenderer, int i, int j, int k, int l, String s)
    {
        isFocused = false;
        isEnabled = true;
        parentGuiScreen = guiscreen;
        fontRenderer = fontrenderer;
        xPos = i;
        yPos = j;
        width = k;
        height = l;
        setText(s);
    }

    public void setText(String s)
    {
        text = s;
    }

    public String getText()
    {
        return text;
    }

    public void updateCursorCounter()
    {
        cursorCounter++;
    }

    public void textboxKeyTyped(char var1, int var2) {
        if(this.isEnabled && this.isFocused) {
           if(var1 == 9) {
              this.parentGuiScreen.selectNextField();
           }

           if(var1 == 22) {
              String var3 = GuiScreen.getClipboardString();
              if(var3 == null) {
                 var3 = "";
              }

              int var4 = 32 - this.text.length();
              if(var4 > var3.length()) {
                 var4 = var3.length();
              }

              if(var4 > 0) {
                 this.text = this.text + var3.substring(0, var4);
              }
           }

           if(var2 == 14 && this.text.length() > 0) {
              this.text = this.text.substring(0, this.text.length() - 1);
           }

           if(ChatAllowedCharacters.allowedCharacters.indexOf(var1) >= 0 && (this.text.length() < this.maxStringLength || this.maxStringLength == 0)) {
              this.text = this.text + var1;
           }

        }
     }

    public void mouseClicked(int i, int j, int k)
    {
        boolean flag = isEnabled && i >= xPos && i < xPos + width && j >= yPos && j < yPos + height;
        setFocused(flag);
    }

    public void setFocused(boolean flag)
    {
        if(flag && !isFocused)
        {
            cursorCounter = 0;
        }
        isFocused = flag;
    }

    public void drawTextBox()
    {
        drawRect(xPos - 1, yPos - 1, xPos + width + 1, yPos + height + 1, 0xffa0a0a0);
        drawRect(xPos, yPos, xPos + width, yPos + height, 0xff000000);
        if(isEnabled)
        {
            boolean flag = isFocused && (cursorCounter / 6) % 2 == 0;
            drawString(fontRenderer, (new StringBuilder()).append(text).append(flag ? "_" : "").toString(), xPos + 4, yPos + (height - 8) / 2, 0xe0e0e0);
        } else
        {
            drawString(fontRenderer, text, xPos + 4, yPos + (height - 8) / 2, 0x707070);
        }
    }

    public void setMaxStringLength(int i)
    {
        maxStringLength = i;
    }

    private final FontRenderer fontRenderer;
    private final int xPos;
    private final int yPos;
    private final int width;
    private final int height;
    private String text;
    private int maxStringLength;
    private int cursorCounter;
    public boolean isFocused;
    public boolean isEnabled;
    private GuiScreen parentGuiScreen;
}
