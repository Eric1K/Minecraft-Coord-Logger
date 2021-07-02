import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JTextArea;

public class FileManager {
	
	Scanner sc;
	PrintWriter pw;
	File file;
	
	public FileManager(File file)
	{
		this.file = file;
		
		try {
			this.sc = new Scanner(file);
			
			ArrayList<String> temp = new ArrayList<String>();
			while(sc.hasNext())
			{
				temp.add(sc.nextLine());
			}
			System.out.println(temp);
			
			this.pw = new PrintWriter(file);
			
			for(int i = 0; i < temp.size(); i++)
			{
				if(i == temp.size()-1)
				{
					pw.print(temp.get(i));
				}else
				{
					pw.println(temp.get(i));
				}
				
			}
			
			for(int i = 0; i < temp.size()/4; i++)
			{
				for(int j = 0; j < 4; j++)
				{
					String str = temp.get((i*4) + j);
					MainFrame.textArea.append(str);
					if(j == 0)
					{
						MainFrame.textArea.append(":");
					}else if(j!=3)
					{
						MainFrame.textArea.append(",");
					}
					MainFrame.textArea.append(" ");

				}
				
				MainFrame.textArea.append("\n");
			}
			
			
			
			pw.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void write(String str) throws FileNotFoundException
	{
		
		if(file.length() == 0)
		{
			pw.print(str);
		}else
		{
			pw.print('\n' + str);
		}
		
		pw.flush();
		System.out.println("Written");
	}
	
	public void clear() throws FileNotFoundException
	{
		PrintWriter temp = new PrintWriter(file);
		
		temp.print("");
		temp.flush();
		
		System.out.println("Cleared");
	}
}
