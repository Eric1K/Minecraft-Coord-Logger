import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class CoordinateTracker{
	File file;
	ArrayList<Float> coords;
	ArrayList<String> coordname;
	FileManager fm;
	Scanner scan;
	
	
	public CoordinateTracker() throws IOException{
		
		file = new File(".storage/coordinates.txt");
		
		if(!file.exists()){
			boolean result = file.createNewFile();
			if(result){
				System.out.println("Successfully created "+file.getAbsolutePath());
			}
			else{
				System.out.println("Failed creating "+file.getAbsolutePath());
			}
		}else{
			System.out.println("Pathname already exists");
		}
		
		coords = new ArrayList<Float>();
		coordname = new ArrayList<String>();
		fm = new FileManager(file);
		
		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean namecoord = true;
		
		while(scan.hasNext() == true)
		{
			if(namecoord == true)
			{
				coordname.add(scan.nextLine());
			}
			
			if(namecoord == false)
			{
				for(int i = 0; i < 3; i++)
				{
					float coordt = Float.parseFloat(scan.nextLine());
					coords.add(coordt);
				}
				
				
			}
			
			
			namecoord = !namecoord;
		}
		
		System.out.println(coordname);
		System.out.println(coords);
		
		
	}
	
	//Adds a selected coordinate
	public void newCoord()
	{
		Scanner sc = new Scanner(System.in);
		//System.out.println("Enter the name of your coord! Type 'cancel' to cancel");
		String name = JOptionPane.showInputDialog("Name:");
		if(name.equals("cancel") == false)
		{
			//System.out.println("Enter the x value");
			
			float x = Float.parseFloat(JOptionPane.showInputDialog("X value:"));
			float y = Float.parseFloat(JOptionPane.showInputDialog("Y value:"));
			float z = Float.parseFloat(JOptionPane.showInputDialog("Z value:"));
			
			//float x = sc.nextFloat();
			//System.out.println("Enter the y value");
			//float y = sc.nextFloat();
			//System.out.println("Enter the z value");
			//float z = sc.nextFloat();
			
			System.out.println(name + " " + x + " " + y + " " + z);
			
			String coords = x + " " + y + " " + z;
			
			coordname.add(name);
			this.coords.add(x);
			this.coords.add(y);
			this.coords.add(z);
			
			try {
				fm.write(name);
				fm.write(String.valueOf(x));
				fm.write(String.valueOf(y));
				fm.write(String.valueOf(z));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		updateTextArea();
		
		
	}
	
	//Deletes a selected coordinate
	public void deleteCoord()
	{
		boolean found = false;
		
		System.out.println();
		Scanner sc = new Scanner(System.in);
		System.out.println("Options: " + coordname);
		System.out.println("Enter the name of your coord to delete! Type 'cancel' to cancel");
		
		String name = JOptionPane.showInputDialog("Name:");
		System.out.println(name + " has been selected");
		
		
		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(name.equals("cancel") == false)
		{
			ArrayList<String> temp = new ArrayList<String>();
			while(scan.hasNext())
			{
				temp.add(scan.nextLine());
			}
			

			
			int pos = -1;
			for(int i = 0; i < temp.size(); i++)
			{
				if(temp.get(i).equals(name) == true)
				{
					pos = i;
					break;
				}
			}
			
			int pos2 = -1;
			for(int i = 0; i < coordname.size(); i++)
			{
				if(coordname.get(i).equals(name) == true)
				{
					pos2 = i;
					found = true;
					break;
				}
				
			}
			
			coordname.remove(pos2);
			
			int tempnum;
			
			if(pos2 != 0)
			{
				tempnum = pos2-1;
			}else
			{
				tempnum = 0;
			}
			
			for(int i = 0; i < 3; i++)
			{
				
				coords.remove((4*(tempnum)));
			}
			
			
			if(pos == -1)
			{
				System.out.println("Unable to find");
			}else {
				PrintWriter pw;
				try {
					pw = new PrintWriter(file);
					
					for(int i = 0; i < temp.size(); i++)
					{
						if(i < pos ||  i > (pos + 3))
						if(i == temp.size()-1)
						{
							pw.print(temp.get(i));
						}else
						{
							pw.println(temp.get(i));
						}
				
					}
					
					pw.flush();
					System.out.println("delete coord written");
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			System.out.println(coordname);
			System.out.println(coords);
			
			updateTextArea();
		}
		
		if(found == false)
		{
			System.out.println("Cannot find name of coordinate");
		}
		
	}
	
	public void updateTextArea()
	{
		MainFrame.textArea.setText("");
		for(int i = 0; i < coordname.size(); i++)
		{
			MainFrame.textArea.append(coordname.get(i));
			MainFrame.textArea.append(": ");
			for(int j = 0; j < 3; j++)
			{
				String str = Float.toString(coords.get((i*3) + j));
				MainFrame.textArea.append(str);
				
				if(j!=2)
				{
					MainFrame.textArea.append(",");
				}
				
				MainFrame.textArea.append(" ");

			}
			
			MainFrame.textArea.append("\n");
		}
	}
	
	public void clear() throws FileNotFoundException
	{
		fm.clear();
		
		coordname = new ArrayList<String>();
		coords = new ArrayList<Float>();
		updateTextArea();
		
		JOptionPane.showConfirmDialog(null, "All coordinates deleted", "Message", JOptionPane.DEFAULT_OPTION);

	}
	
	
	
}
