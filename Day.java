import javax.swing.*;
import java.awt.Component;
import java.awt.event.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Day
{

        // member variables
        private String fileName;
        public String dayContents;


        // for file I/O
        FileReader fr = null;
        FileWriter fw = null;
        BufferedReader br = null;
        BufferedWriter bw = null;


        public Day(String today)
        {
                // date will be in form DDMMM
                this.fileName = today;
                this.dayContents = "";
        }

        public editDay()
        {
                // if the file exists
                if(this.fileName != "")
                {
                        fr = new FileReader(this.fileName);
                        //br = new BufferedReader(fr);
                }
                else
                {

                }
        }


}
