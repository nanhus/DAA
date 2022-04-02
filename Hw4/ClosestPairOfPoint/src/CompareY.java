//Lớp này thực hiện Bộ so sánh và có thể tuần tự hóa và ghi đè so sánh của So sánh (Đối tượng o1, Đối tượng o2)
import java.util.Comparator;

public class CompareY implements Comparator<Point>, java.io.Serializable 
{
	@Override
	public int compare(Point p1, Point p2)
	{
		if(p1.getY()<p2.getY())
		{
			return -1;
		}else if(p1.getY()==p2.getY())
		{
			if(p1.getX()<p2.getX())
			{
				return -1;
			}else if(p1.getX()==p2.getX())
			{
				return 0;
			}else
			{
				return 1;
			}
		}else
		{
			return 1;
		}
	}
}


	