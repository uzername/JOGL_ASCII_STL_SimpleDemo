/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogl_prj2;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.glu.GLU;

public class JavaRenderer2 implements GLEventListener {
    //horizontal rotation component
   public float rotateT = 0.0f;
    //vertical rotation component
   public float rotateV = 0.0f;
   public float scaleFactor = 1.0f;
   private static final GLU glu = new GLU();
       // https://stackoverflow.com/questions/3515059/how-to-rotate-a-specific-object-in-opengl
   public void display(GLAutoDrawable gLDrawable) {
       final GL2 gl = gLDrawable.getGL().getGL2();
       gl.glClear(GL.GL_COLOR_BUFFER_BIT);
       gl.glClear(GL.GL_DEPTH_BUFFER_BIT);
       gl.glLoadIdentity();
       gl.glTranslatef(0.0f, 0.0f, -5.0f);

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