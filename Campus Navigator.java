package dsapro;
import java.util.*;

import java.util.Stack;
class node//class node of name and meaning
{
	String name, lab_subject  , faculty_lecture  ;
	String mailid;
	String freetime;
	String subject;
	int classroomno;
	int floor;//classroom
	int floorno ;   //lab floor 
	
	node left;
	node right;
	node(String n,String m,String subj,String ft)//parameterised constructor to initialize name and meaning
	{
		name=n;
		mailid=m;
		subject=subj;
		freetime=ft;
		left=null;
		right=null;
	}//single node is being created
	/*node(int cno,int f)
	{
		classroomno=cno;
		floor=f;
	}*/
	node()
	{
		
	}
}
class BSTree//class to create tree
{
	node root;
	node root2;//for classroom
	node root3 ; //for lab
	BSTree()//default constructor
	{
		root=null;
	}
	void create()//create teacher tree
	{
		int yn;
		Scanner s=new Scanner(System.in);
		node temp;
		String name1,mailid1,sub;
		String freetime1;
		String freetime2;
		int flag=0,dir;
		System.out.println("Enter name of the teacher");
		name1=s.nextLine();
		System.out.println("Enter mailid");
		mailid1=s.next();
		System.out.println("Enter free time:");
	    freetime1=s.next();
	    System.out.println("Enter subject:");
	    sub=s.next();
		temp=new node(name1,mailid1,sub,freetime1);//creates node of name and meaning
		if(root==null)
		{
			root=temp;
			flag=1;
		}//initialises first node
		else 
		{
			node ptr;
			ptr=root;
			while(flag==0)
			{
				if(ptr.name.compareTo(temp.name)>0)//compares aplphabetically
				{
					if(ptr.left==null)
					{
						ptr.left=temp;
						flag=1;//indication of adding new node to left
					}
					else
					{
						ptr=ptr.left;
					}
				}
				else
				{
					if(ptr.right==null)
					{
						ptr.right=temp;
						flag=1;//indication of adding new node
					}
					else
					{
						ptr=ptr.right;
					}
				}
			}
		}
		System.out.println("Do you want to add more:1.yes 2.no");
		dir=s.nextInt();//asks for adding more
		if(dir==1)
		{
			create();
		}//repeting crete until 'no'.
	}
	void inOrder(node lroot)//teacher tree display
	{
		if(lroot!=null)
		{
			inOrder(lroot.left);
			System.out.println("Name:"+lroot.name);
			System.out.println("Mailid:"+lroot.mailid);
			System.out.println("Subject:"+lroot.subject);
			System.out.println("Freetime:"+lroot.freetime);
			inOrder(lroot.right);
		}
		System.out.println();
	}//displays whole list in order
	
	void search() //teacher tree search by teacher name
	{
		Scanner s=new Scanner(System.in);
		String w;
		node ptr;
		int i=1;
		
		System.out.println("Enter the name you want to Search. ");
		w=s.nextLine();
		
		if(root.name.compareToIgnoreCase(w)==0)
		{
			System.out.println(root.name+ "-" +root.subject);
			i++;
		}
		else {
			ptr=root;
			while(ptr!=null)
			{
				
				if(ptr.name.compareToIgnoreCase(w)<0)
				{
					ptr=ptr.right;
				}
				else if(ptr.name.compareToIgnoreCase(w)>0){
					ptr=ptr.left;
					
				}
				else if(ptr.name.compareToIgnoreCase(w)==0){
					
					System.out.println("YOU SEARCHED FOR : "+ ptr.name);
					System.out.println("Subject taught :"+ptr.subject);
					System.out.println("Freetime is:"+ptr.freetime);
					i++;
					ptr=null;
				}
			}
			}
		if(i==1)
		{
			System.out.println("Search Unsuccessful. ");
		}
		
	}
	
	void search1()//teacher tree search by subject
	{
		Scanner s=new Scanner(System.in);
		System.out.println("Enter the subject you want to Search. ");
		String m=s.next();
		Stack<node> st=new Stack<node>();
		node ptr=root;
		while(st.isEmpty()!=true ||ptr!=null)
		{
					while(ptr!=null)
					{
						st.push(ptr);
						ptr=ptr.left;
					}
					if(st.isEmpty() )
					{
						break;
					}
					else
					{
						ptr=st.pop();
						if(ptr.subject.compareToIgnoreCase(m)==0)
						{
						System.out.println("SUBJECT YOU SEARCHED FOR : "+ptr.subject);
						System.out.println("NAME OF THE TEACHER: "+ptr.name);
						System.out.println("EMAIL ID: "+ptr.mailid);
						}
						ptr=ptr.right;
						
					}
		       }
		System.out.println();
			
		}
		
		
		
	
	void update() //update teacher tree
	{
		Scanner s=new Scanner(System.in);
		String key;
		System.out.println("Enter the name of teacher");
		key=s.nextLine();
		node ptr;
		int flag=0;
		ptr=root;
		while(flag==0&&ptr!=null)
		{
			if(ptr.name.compareTo(key)==0)
			{
				flag=1;
			}
			else if(ptr.name.compareTo(key)>0)
			{
				ptr=ptr.left;
			}
			else
			{
				ptr=ptr.right;
			}
		}
		if(flag==1)
		{
			System.out.println("Data found");
			System.out.println("Enter new freetime");
			String mean=s.next();
			ptr.freetime=mean;
			System.out.println("Name :"+ptr.name);
			System.out.println("Freetime :"+ptr.freetime); 
		}
		else
		{
			System.out.println("Data not found");
		}
	}//similar to search and updates meaning.
	void delete()//delete teacher tree
	{
		Scanner s=new Scanner(System.in);
		String key;
		System.out.println("Enter name of the teacher to delete");
		key=s.nextLine();
		node ptr,parent;
		int flag=0;
		ptr=root;
		parent=root;
		while(flag==0&&ptr!=null)
		{ 
			if(ptr.name.compareTo(key)==0)
			{
				flag=1;
			}
			else if(ptr.name.compareTo(key)>0)
			{
				parent=ptr;
				ptr=ptr.left;
			}
			else
			{
				parent=ptr;
				ptr=ptr.right;
			}
		}//searches node in tree.
		if(flag==1)
		{
			if(ptr==root&&ptr.left==null&&ptr.right==null)
			{ 
				root=null;
				System.out.println("Tree is empty");				
			}
			else
			{
			System.out.println("Data found to delete");
			System.out.println("Name :"+ptr.name);
			System.out.println("Freetime :"+ptr.freetime);
			System.out.println("Mail id :"+ptr.mailid);
			if(ptr.left==null&&ptr.right==null)
			{
				if(parent.name.compareTo(ptr.name)>0)
				{
					parent.left=null;
				}
				else
				{
					parent.right=null;
				}
			}//for node to be leaf.
			else if(ptr.left!=null&&ptr.right==null)
			{
				if(parent.name.compareTo(ptr.name)>0)
				{
					parent.left=ptr.left;
				}
				else
				{
					parent.right=ptr.left;
				}
				ptr.left=null;
			}//for left subtree present
			else if(ptr.left==null&&ptr.right!=null)
			{
				if(parent.name.compareTo(ptr.name)>0)
				{
					parent.left=ptr.right;
				}
				else
				{
					parent.right=ptr.right;
				}
			}//for right subtree present
			else
			{
				node p;
				p=ptr.left;
				parent=ptr;
				while(p.right!=null)
				{
					parent=p;
					p=p.right;
				}
				ptr.name=p.name;
				ptr.freetime=p.freetime;
				ptr.mailid=p.mailid;
				if(parent.name!=ptr.name)
				{
					parent.right=p.left; 
				}
				if(parent.name.compareTo(ptr.name)==0)
				{
					parent.left=p.left;
				}
			}//for both subtree present.
			}
		}
		else
		{
			System.out.println("Data not found");
		}//when word searches not found to delete.
	}
	void insertclass() //create tree for classroom
	{
	 
		int classnum , floornum ; 
		int flag = 0;
		Scanner s = new Scanner(System.in);
		
		System.out.println("Enter the classroom number :");
		classnum = s.nextInt();
		System.out.println("Enter the floor number :");
		floornum = s.nextInt();
		node temp = new node();
		
		temp.classroomno = classnum;
		temp.floor=floornum;
		if(root2==null)
		{
			root2=temp;
			flag=1;
		}
		else
		{
			node ptr1=root2;
			while(flag==0)
			{

				if(temp.classroomno<ptr1.classroomno ) 
				{
					if(ptr1.left!=null)
					{
						ptr1=ptr1.left;
					}
					else
					{
						ptr1.left=temp;
						flag=1;
					}
				}
				else
				{
					if(temp.classroomno >ptr1.classroomno ) 
					{
						if(ptr1.right!=null)
						{
							ptr1=ptr1.right;
						}
						else
						{
							ptr1.right=temp;
							flag=1;
						}
					}
				}
				
			}
		}
		
	}
 
void inorder1(node cur)//Display for classroom tree
	{
		if(cur!=null)
		{
			inorder1(cur.left);
			System.out.println("classroomno:"+cur.classroomno);
			System.out.println("Floor:"+cur.floor);
			inorder1(cur.right);
		}
		System.out.println();
	}//displays whole list in order
    
 
int search(int q)//search for classroom tree
{
	node ptr = root2;
	node temp=null;
	int flag = 0;
	while(ptr != null)
	{
		if(q==ptr.classroomno)
		{
			flag = 1;
			temp=ptr;
			break;
		}
		else if(q<ptr.classroomno)  
		{
			ptr = ptr.left;
		}
		else if(q > ptr.classroomno)
		{
			ptr = ptr.right;
		}
	}
	if(flag==1) {
		System.out.println("THE CLASS HAS BEEN FOUND");
		System.out.println(temp.classroomno+" =  " )  ; 
		System.out.print( "the floor is :   "+ temp.floor );
	}
	else {
		System.out.println("THE CLASS ISNT PRESENT IN THIS BUILDING ");
	}
	return flag;
} 
void insertlab()  //create tree for lab
{   
	String faculty_practical ;    //name of faculty conducting this lab 
	
	 Scanner s=new Scanner(System.in) ; 
		String lab_subj = "" ;  //subject for which lab is being conducted 
		int flag = 0; 
		int floor_no ; 

		System.out.println("Enter subject for lab :");
		lab_subj= s.next();
		System.out.println("Enter the floor where lab is located :");
		floor_no= s.nextInt(); 
		
		node temp = new node();
		
		temp.lab_subject = lab_subj;
		temp.floorno= floor_no ;  
		
		if(root3==null)
		{
			root3=temp;
			flag=1;
		}
		else
		{
			node ptr1=root3;
			while(flag==0)
			{

				if(temp.lab_subject.compareTo(ptr1.lab_subject)<0)
				{
					if(ptr1.left!=null)
					{
						ptr1=ptr1.left;
					}
					else
					{
						ptr1.left=temp;
						flag=1;
					}
				}
				else
				{
					if(temp.lab_subject.compareTo(ptr1.lab_subject)>0)
					{
						if(ptr1.right!=null)
						{
							ptr1=ptr1.right;
						}
						else
						{
							ptr1.right=temp;
							flag=1;
						}
					}
				}
				
			}
		}
		
	}


void inorder2(node cur1)//Display for labs
{
	if(cur1!=null)
	{
		inorder2(cur1.left);
		System.out.println("subject for lab :"+cur1.lab_subject);
		System.out.println("Floor:"+cur1.floorno); 
		inorder2(cur1.right);
	}
	System.out.println();
}//displays whole list in order 
int search(String r)   //searching lab for lab tree
{
	node ptr = root3;
	node temp=null;
	int flag = 0;
	while(ptr != null)
	{
		if(r.equals(ptr.lab_subject))
		{
			flag = 1;
			temp=ptr;
			break;
		}
		else if(r.compareTo(ptr.lab_subject) < 0)
		{
			ptr = ptr.left;
		}
		else if(r.compareTo(ptr.lab_subject) > 0)
		{
			ptr = ptr.right;
		}
	}
	if(flag==1) {
		System.out.println("THE LAB HAS BEEN FOUND");
		System.out.println(temp.lab_subject) ;
		System.out.println(" FLOOR  :   "+ temp.floorno) ;  
  
		
	}
	else {
		System.out.println("THE LAB IS NOT PRESENT IN THE BUILDING");
	}
	return flag;
}

}
public class dsapro {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s=new Scanner(System.in);
		BSTree b=new BSTree();
		int ch=0;
		int z=0;
		int yn=0;
		int i;
		int pass;
		do {
		System.out.println("1.For college staff\n2.For classroom creation\n3.For student access\n4.Exit");
		z=s.nextInt();
		switch(z)
		{
		case 1://for teacher creation
			do {
				System.out.println("Enter password to access:");
				pass=s.nextInt();
				
				if(pass==123456)
				{
				
				      do {
							System.out.println("MENU\n1.create\n2.display\n3.update\n4.delete\n5.exit");
							ch=s.nextInt();
							switch(ch)
							{
							case 1:b.create();
							break;
							case 2:b.inOrder(b.root);
							break;
							case 3:b.update();
							break;
							case 4:b.delete();
							break;
							case 5:System.out.println("Thankyou");
							break;
							}
						
				      }while(ch<5);
				}
				else
				{
					System.out.println("Access Denied");
					
				}
			}while(pass!=123456);
		    break;
		case 2://for classroom and labs creation
			do {
				System.out.println("Enter password to access:");
				pass=s.nextInt();
				
						if(pass==9876543)
						{	
							do {
								System.out.println("MENU\n1.create classroom\n2.display classroom\n3.create labs\n4.display labs\n5.Exit");
								ch=s.nextInt();
								switch(ch)
								{
								case 1:
									do {
									b.insertclass();
									System.out.println("DO YOU WANT TO CONTINUE?(1=YES /2=N0)");
								    yn=s.nextInt();
									}while(yn!=2);
									break;
								case 2:
									b.inorder1(b.root2); 
									break ; 
								case 3:
									do {
										b.insertlab();
										System.out.println("DO YOU WANT TO CONTINUE?(1=YES /2=N0)");
									    i=s.nextInt();
										}while(i!=2);
										break;  
								case 4:b.inorder2(b.root3);
								break ; 
								case 5:System.out.println("Thankyou");
								break;
								}
							}while(ch<5);
						}	
						else
						{
							System.out.println("Access Denied");
							
						}
				}while(pass!=9876543);	
			break;
		case 3://for student search
			int choice=0;
			do {
				System.out.println("MENU\n1.search for teacher and subject\n2.search for classroom\n3.search for labs\n4.exit");
				choice=s.nextInt();
				switch(choice)
				{
				case 1:do {
					System.out.println("1.Search by teacher\n2.search by subject");
					int h=s.nextInt();
					switch(h)
					{
					case 1:b.search();
					break;
					case 2:b.search1();
					break;
					}
				 }while(ch<3);
				break;
				case 2:System.out.println( " enter the class room number  you want to search =   ") ;
				int q=s.nextInt();
				b.search(q);
				System.out.println(" ");
				break;
				case 3:  //searching labs 
					System.out.println( " enter the subject of lab  you want to search =   ") ;
					String r=s.next();
					b.search(r);
					System.out.println(" ");
					break;  
				case 4:System.out.println("THANK YOU");
				break;
				}
			}while(choice<4);
		case 4:System.out.println("THANK YOU");
		break;
			
		}
		
	}while(z<=3);

	}

}
/*
1.For college staff
2.For classroom creation
3.For student access
4.Exit
1
Enter password to access:
12345
Access Denied
Enter password to access:
123456
MENU
1.create
2.display
3.update
4.delete
5.exit
1
Enter name of the teacher
Neha Koria
Enter mailid
neha.koria@gmail.com
Enter free time:
2.00-2.10,3.00-3.30
Enter subject:
OS
Do you want to add more:1.yes 2.no
1
Enter name of the teacher
Saili Sapkal
Enter mailid
saili.sapkal@gmail.com
Enter free time:
1.00-2.00,3.00-3.30
Enter subject:
MA
Do you want to add more:1.yes 2.no
1
Enter name of the teacher
Geetanjali Salunkhe
Enter mailid
geetanjali.salunkhe@gmail.com
Enter free time:
12.00-1.00,3.00-3.30
Enter subject:
DSA
Do you want to add more:1.yes 2.no
1
Enter name of the teacher
Varsha Pimprale
Enter mailid
varsha.pimprale@gmail.com
Enter free time:
3.00-3.30,4.00-5.00
Enter subject:
OS
Do you want to add more:1.yes 2.no
1
Enter name of the teacher
Pradnya Nanavare
Enter mailid
pradnya.nanavare@gmail.com
Enter free time:
2.00-4.00
Enter subject:
EM3
Do you want to add more:1.yes 2.no
2
MENU
1.create
2.display
3.update
4.delete
5.exit
2

Name:Geetanjali Salunkhe
Mailid:geetanjali.salunkhe@gmail.com
Subject:DSA
Freetime:12.00-1.00,3.00-3.30


Name:Neha Koria
Mailid:neha.koria@gmail.com
Subject:OS
Freetime:2.00-2.10,3.00-3.30

Name:Pradnya Nanavare
Mailid:pradnya.nanavare@gmail.com
Subject:EM3
Freetime:2.00-4.00


Name:Saili Sapkal
Mailid:saili.sapkal@gmail.com
Subject:MA
Freetime:1.00-2.00,3.00-3.30

Name:Varsha Pimprale
Mailid:varsha.pimprale@gmail.com
Subject:OS
Freetime:3.00-3.30,4.00-5.00

MENU
1.create
2.display
3.update
4.delete
5.exit
3
Enter the name of teacher
Pradnya Nanavare
Data found
Enter new freetime
4.00-6.00
Name :Pradnya Nanavare
Freetime :4.00-6.00
MENU
1.create
2.display
3.update
4.delete
5.exit
4
Enter name of the teacher to delete
Pradnya Nanavare
Data found to delete
Name :Pradnya Nanavare
Freetime :4.00-6.00
Mail id :pradnya.nanavare@gmail.com
MENU
1.create
2.display
3.update
4.delete
5.exit
4
Enter name of the teacher to delete
Saili Sapkal
Data found to delete
Name :Saili Sapkal
Freetime :1.00-2.00,3.00-3.30
Mail id :saili.sapkal@gmail.com
MENU
1.create
2.display
3.update
4.delete
5.exit
2

Name:Geetanjali Salunkhe
Mailid:geetanjali.salunkhe@gmail.com
Subject:DSA
Freetime:12.00-1.00,3.00-3.30


Name:Neha Koria
Mailid:neha.koria@gmail.com
Subject:OS
Freetime:2.00-2.10,3.00-3.30

Name:Varsha Pimprale
Mailid:varsha.pimprale@gmail.com
Subject:OS
Freetime:3.00-3.30,4.00-5.00



MENU
1.create
2.display
3.update
4.delete
5.exit
1
Enter name of the teacher
Saili Sapkal
Enter mailid
saili.sapkal@gmail.com
Enter free time:
1.00-2.00,3.00-3.30
Enter subject:
MA
Do you want to add more:1.yes 2.no
2
MENU
1.create
2.display
3.update
4.delete
5.exit
2

Name:Geetanjali Salunkhe
Mailid:geetanjali.salunkhe@gmail.com
Subject:DSA
Freetime:12.00-1.00,3.00-3.30


Name:Neha Koria
Mailid:neha.koria@gmail.com
Subject:OS
Freetime:2.00-2.10,3.00-3.30

Name:Saili Sapkal
Mailid:saili.sapkal@gmail.com
Subject:MA
Freetime:1.00-2.00,3.00-3.30


Name:Varsha Pimprale
Mailid:varsha.pimprale@gmail.com
Subject:OS
Freetime:3.00-3.30,4.00-5.00



MENU
1.create
2.display
3.update
4.delete
5.exit
5
Thankyou
1.For college staff
2.For classroom creation
3.For student access
4.Exit
2
Enter password to access:
9876543
MENU
1.create classroom
2.display classroom
3.create labs
4.display labs
5.Exit
1
Enter the classroom number :
1
Enter the floor number :
1
DO YOU WANT TO CONTINUE?(1=YES /2=N0)
1
Enter the classroom number :
4
Enter the floor number :
2
DO YOU WANT TO CONTINUE?(1=YES /2=N0)
1
Enter the classroom number :
8
Enter the floor number :
2
DO YOU WANT TO CONTINUE?(1=YES /2=N0)
1
Enter the classroom number :
18
Enter the floor number :
5
DO YOU WANT TO CONTINUE?(1=YES /2=N0)
1
Enter the classroom number :
13
Enter the floor number :
4
DO YOU WANT TO CONTINUE?(1=YES /2=N0)
2
MENU
1.create classroom
2.display classroom
3.create labs
4.display labs
5.Exit
2

classroomno:1
Floor:1

classroomno:4
Floor:2

classroomno:8
Floor:2

classroomno:13
Floor:4


classroomno:18
Floor:5


MENU
1.create classroom
2.display classroom
3.create labs
4.display labs
5.Exit
3
Enter subject for lab :
DSA
Enter the floor where lab is located :
5
DO YOU WANT TO CONTINUE?(1=YES /2=N0)
1
Enter subject for lab :
OS
Enter the floor where lab is located :
3
DO YOU WANT TO CONTINUE?(1=YES /2=N0)
1
Enter subject for lab :
MA
Enter the floor where lab is located :
3
DO YOU WANT TO CONTINUE?(1=YES /2=N0)
1
Enter subject for lab :
ELECTRICAL
Enter the floor where lab is located :
4
DO YOU WANT TO CONTINUE?(1=YES /2=N0)
1
Enter subject for lab :
FCN
Enter the floor where lab is located :
5
DO YOU WANT TO CONTINUE?(1=YES /2=N0)
2
MENU
1.create classroom
2.display classroom
3.create labs
4.display labs
5.Exit
4

subject for lab :DSA
Floor:5

subject for lab :ELECTRICAL
Floor:4

subject for lab :FCN
Floor:5

subject for lab :MA
Floor:3

subject for lab :OS
Floor:3



MENU
1.create classroom
2.display classroom
3.create labs
4.display labs
5.Exit
5
Thankyou
1.For college staff
2.For classroom creation
3.For student access
4.Exit
3
MENU
1.search for teacher and subject
2.search for classroom
3.search for labs
4.exit
1
1.Search by teacher
2.search by subject
1
Enter the name you want to Search. 
Neha Koria
Neha Koria-OS
MENU
1.search for teacher and subject
2.search for classroom
3.search for labs
4.exit
1
1.Search by teacher
2.search by subject
2
Enter the subject you want to Search. 
OS
SUBJECT YOU SEARCHED FOR : OS
NAME OF THE TEACHER: Neha Koria
EMAIL ID: neha.koria@gmail.com
SUBJECT YOU SEARCHED FOR : OS
NAME OF THE TEACHER: Varsha Pimprale
EMAIL ID: varsha.pimpare

MENU
1.search for teacher and subject
2.search for classroom
3.search for labs
4.exit
2
 enter the class room number  you want to search =   
13
THE CLASS HAS BEEN FOUND
13 =  
the floor is :   4 
MENU
1.search for teacher and subject
2.search for classroom
3.search for labs
4.exit
3
 enter the subject of lab  you want to search =   
DSA
THE LAB HAS BEEN FOUND
DSA
 FLOOR  :   5
 
MENU
1.search for teacher and subject
2.search for classroom
3.search for labs
4.exit
4
THANK YOU
THANK YOU
1.For college staff
2.For classroom creation
3.For student access
4.Exit
4
THANK YOU


 */
