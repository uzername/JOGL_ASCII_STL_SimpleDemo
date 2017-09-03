/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RenderManagement;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.glu.GLU;
import jogl_prj2.STLMeshStruct;

public class JavaRenderer2 implements GLEventListener {
    //horizontal rotation component
   public float rotateT = 0.0f;
    //vertical rotation component
   public float rotateV = 0.0f;
   public float scaleFactor = 1.0f;
   private static final GLU glu = new GLU();
   
   public RenderManager myRenderManagementUtil = new RenderManager();
       /**
        * used to display items on scene. FPSAnimator object is being used, so this routine is being called in cycle by jogl
        * @param gLDrawable 
        */
   public void display(GLAutoDrawable gLDrawable) {
       final GL2 gl = gLDrawable.getGL().getGL2();
       gl.glClear(GL.GL_COLOR_BUFFER_BIT);
       gl.glClear(GL.GL_DEPTH_BUFFER_BIT);
       gl.glLoadIdentity();
       class PYRMYDrenderer {
            void renderPYRMYD() {
                gl.glTranslatef(0.0f, 0.0f, -5.0f);
                //gl is declared on higher level. a closure!
                // https://stackoverflow.com/questions/3515059/how-to-rotate-a-specific-object-in-opengl
                gl.glRotatef(rotateV, 1.0f, 0.0f, 0.0f);
                gl.glRotatef(rotateT, 0.0f, 1.0f, 0.0f);
                //gl.glRotatef(rotateT, 0.0f, 0.0f, 1.0f);
                //gl.glRotatef(rotateT, 0.0f, 1.0f, 0.0f);
                gl.glScalef( scaleFactor,scaleFactor,scaleFactor);

                gl.glBegin(GL2.GL_TRIANGLES);

                // Front
                gl.glColor3f(0.0f, 1.0f, 1.0f); 
                gl.glVertex3f(0.0f, 1.0f, 0.0f);
                gl.glColor3f(0.0f, 0.0f, 1.0f); 
                gl.glVertex3f(-1.0f, -1.0f, 1.0f);
                gl.glColor3f(0.0f, 0.0f, 0.0f); 
                gl.glVertex3f(1.0f, -1.0f, 1.0f);

                // Right Side Facing Front
                gl.glColor3f(0.0f, 1.0f, 1.0f); 
                gl.glVertex3f(0.0f, 1.0f, 0.0f);
                gl.glColor3f(0.0f, 0.0f, 1.0f); 
                gl.glVertex3f(1.0f, -1.0f, 1.0f);
                gl.glColor3f(0.0f, 0.0f, 0.0f); 
                gl.glVertex3f(0.0f, -1.0f, -1.0f);

                // Left Side Facing Front
                gl.glColor3f(0.0f, 1.0f, 1.0f); 
                gl.glVertex3f(0.0f, 1.0f, 0.0f);
                gl.glColor3f(0.0f, 0.0f, 1.0f); 
                gl.glVertex3f(0.0f, -1.0f, -1.0f);
                gl.glColor3f(0.0f, 0.0f, 0.0f); 
                gl.glVertex3f(-1.0f, -1.0f, 1.0f);

                // Bottom
                gl.glColor3f(0.0f, 0.0f, 0.0f); 
                gl.glVertex3f(-1.0f, -1.0f, 1.0f);
                gl.glColor3f(0.1f, 0.1f, 0.1f); 
                gl.glVertex3f(1.0f, -1.0f, 1.0f);
                gl.glColor3f(0.2f, 0.2f, 0.2f); 
                gl.glVertex3f(0.0f, -1.0f, -1.0f);

                gl.glEnd();

                //rotateT += 0.2f;
            }
       }
       class STLrendererLocal {
            void renderCachedSTL_DATA() {
                gl.glTranslatef(0.0f, 0.0f, -5.0f);
               gl.glRotatef(rotateV, 1.0f, 0.0f, 0.0f);
               gl.glRotatef(rotateT, 0.0f, 1.0f, 0.0f);
                //gl.glRotatef(rotateT, 0.0f, 0.0f, 1.0f);
                //gl.glRotatef(rotateT, 0.0f, 1.0f, 0.0f);
               gl.glScalef( scaleFactor,scaleFactor,scaleFactor);
               gl.glBegin(GL2.GL_TRIANGLES);
                for (STLMeshStruct singleMeshIter : myRenderManagementUtil.wholeCachedStruct) {
                    //gl.glColor3f(0.0f, 1.0f, 1.0f); 
                    gl.glColor3f(singleMeshIter.redColorComponents.get(0), singleMeshIter.greenColorComponents.get(0), singleMeshIter.blueColorComponents.get(0));
                    gl.glVertex3f( singleMeshIter.Vert1.get(0).floatValue(), singleMeshIter.Vert1.get(1).floatValue(), singleMeshIter.Vert1.get(2).floatValue() );
                    gl.glColor3f(singleMeshIter.redColorComponents.get(1), singleMeshIter.greenColorComponents.get(1), singleMeshIter.blueColorComponents.get(1));
                    gl.glVertex3f( singleMeshIter.Vert2.get(0).floatValue(), singleMeshIter.Vert2.get(1).floatValue(), singleMeshIter.Vert2.get(2).floatValue() );
                    gl.glColor3f(singleMeshIter.redColorComponents.get(2), singleMeshIter.greenColorComponents.get(2), singleMeshIter.blueColorComponents.get(2));
                    gl.glVertex3f( singleMeshIter.Vert3.get(0).floatValue(), singleMeshIter.Vert3.get(1).floatValue(), singleMeshIter.Vert3.get(2).floatValue() );
                }
               
               gl.glEnd();
            }
       }
       PYRMYDrenderer myPyrmydRenderer = new PYRMYDrenderer();
       STLrendererLocal mySTLrenderer = new STLrendererLocal();
       switch(RenderManager.renderContext) {
           case SQUARE_POT: {
               
               //it is assumed that required type of mesh has been cached before in main routine
               //need to display it.
               //myRenderManagementUtil.cacheSquarePot();
               //myPyrmydRenderer.renderPYRMYD();
               gl.glTranslatef(0.0f, -1.5f, 0.0f);
               
               mySTLrenderer.renderCachedSTL_DATA();
               break;
           }
           case CLAY_POT: {
               gl.glTranslatef(0.0f, -1.5f, 0.0f);
               mySTLrenderer.renderCachedSTL_DATA();
               break;
           }
           default: { //render PYRMYD
               myPyrmydRenderer.renderPYRMYD();
               break;
           }
       }

   }

   public void init(GLAutoDrawable gLDrawable) {
       final GL2 gl = gLDrawable.getGL().getGL2();
       gl.glShadeModel(GL2.GL_SMOOTH);
       gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
       gl.glClearDepth(1.0f);
       gl.glEnable(GL.GL_DEPTH_TEST);
       gl.glDepthFunc(GL.GL_LEQUAL);
       gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT,GL.GL_NICEST);
   }

 public void reshape(GLAutoDrawable gLDrawable, int x, 
   int y, int width, int height) {
       final GL2 gl = gLDrawable.getGL().getGL2();
       if(height <= 0) {
           height = 1;
       }
       final float h = (float)width / (float)height;
       gl.glMatrixMode(GL2.GL_PROJECTION);
       gl.glLoadIdentity();
       glu.gluPerspective(50.0f, h, 1.0, 1000.0);
       gl.glMatrixMode(GL2.GL_MODELVIEW);
       gl.glLoadIdentity();
   }

public void dispose(GLAutoDrawable arg0) {
	
}

}