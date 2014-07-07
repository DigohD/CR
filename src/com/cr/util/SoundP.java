package com.cr.util;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

public class SoundP{
		
	private static HashMap<String, String> soundLib = 
			new HashMap<String, String>();
	
	public SoundP(){
		
		// misc
		soundLib.put("lootpickup", "/misc/lootpickup.wav");
		soundLib.put("lootdrop1", "/misc/lootdrop1.wav");
		soundLib.put("lootdrop2", "/misc/lootdrop2.wav");
		soundLib.put("lootdrop3", "/misc/lootdrop3.wav");
		
		// weapons
		soundLib.put("bladeswing1", "/weapon/bladeswing1.wav");
		soundLib.put("bladeswing2", "/weapon/bladeswing2.wav");
		soundLib.put("bladeswing3", "/weapon/bladeswing3.wav");
		
		soundLib.put("bladehit1", "/weapon/bladehit1.wav");
		soundLib.put("bladehit2", "/weapon/bladehit2.wav");
		
		// hero
		soundLib.put("step1", "/hero/step1.wav");
		soundLib.put("step2", "/hero/step2.wav");
		soundLib.put("step3", "/hero/step3.wav");
		soundLib.put("step4", "/hero/step4.wav");
		
		soundLib.put("pushed1", "/hero/pushed1.wav");
		soundLib.put("pushed2", "/hero/pushed2.wav");
		
		soundLib.put("ouch1", "/hero/ouch1.wav");
		soundLib.put("ouch2", "/hero/ouch2.wav");
		soundLib.put("ouch3", "/hero/ouch3.wav");
	}
	
	public static void playSound(final String name) {
		new Thread(new Runnable()
		{
		    SourceDataLine soundLine;
		    @Override
			public void run()
		    {
		      soundLine = null;
		          int BUFFER_SIZE = 1024*1024;  // 64 KB

		              // Set up an audio input stream piped from the sound file.
		              try {
		                 AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource("/sounds/" + soundLib.get(name)));
		                 AudioFormat audioFormat = audioInputStream.getFormat();
		                 DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
		                 soundLine = (SourceDataLine) AudioSystem.getLine(info);
		                 soundLine.open(audioFormat);
		                 soundLine.start();
		                 int nBytesRead = 0;
		                 byte[] sampledData = new byte[BUFFER_SIZE];
		                 while (nBytesRead != -1) 
		                 {
		                    nBytesRead = audioInputStream.read(sampledData, 0, sampledData.length);
		                    if (nBytesRead >= 0) 
		                    {
		                       // Writes audio data to the mixer via this source data line.
		                       soundLine.write(sampledData, 0, nBytesRead);
		                    }

		                 }
		              } catch (Exception ex) 
		              {
		                 ex.printStackTrace();
		              }finally 
		              {
		                 soundLine.drain();
		                 soundLine.close();
		              }
		        }
		}).start();
	}

	
}

	
	


