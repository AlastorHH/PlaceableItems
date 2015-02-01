package com.stuntmania.PlaceableItems.Renderers;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.stuntmania.PlaceableItems.PlaceableItems;
import com.stuntmania.PlaceableItems.Models.DustModel;
import com.stuntmania.PlaceableItems.TileEntities.PlaceableItemsTileEntity;

public class GunpowderBlockRenderer extends TileEntitySpecialRenderer
{
    DustModel model = new DustModel();
    ResourceLocation gunpowder = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/gunpowder.png");
	
    @Override
    public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float scale) {
	PlaceableItemsTileEntity gunpowderEntity = (PlaceableItemsTileEntity) entity;
		
	bindTexture(gunpowder);
		
        GL11.glPushMatrix();
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
	GL11.glScalef(1.0F, -1.0F, -1.0F);
        int facing = gunpowderEntity.getFacing();
        int k = 0;
        k = facing * 90;
        GL11.glRotatef(k, 0.0F, 1.0F, 0.0F);
        
        model.Shape1.render(0.0625F);
        model.Shape2.render(0.0625F);
        model.Shape3.render(0.0625F);
        model.Shape4.render(0.0625F);
        model.Shape5.render(0.0625F);
        model.Shape6.render(0.0625F);
        model.Shape7.render(0.0625F);
        model.Shape8.render(0.0625F);
        model.Shape9.render(0.0625F);
        model.Shape10.render(0.0625F);
        model.Shape11.render(0.0625F);
        model.Shape12.render(0.0625F);
        model.Shape13.render(0.0625F);
        model.Shape14.render(0.0625F);
        GL11.glPopMatrix();
    }
}