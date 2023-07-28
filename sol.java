package misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class main_misc {

	public static void main(String[] args) {
		

		List<Integer> bars =  new ArrayList<>(Arrays.asList(0,0,4,0,0,6,0,0,3,0,5,0,1,0,0,0));
		System.out.println(histogram_vol(bars));
		
	}
	
	private static int calc_vol(List<Integer> bars,int strt,int end,int[] ptr_i,boolean plus){
		
		int cmp = bars.get(strt);
		int negative_vol = 0;
		if(plus){
			
			for(int i = strt+1;i <= end;i++){
				
				if(bars.get(i) > cmp){
					ptr_i[0] = i-1;
					return ((int)(Math.abs(i-1-strt))*cmp) - negative_vol;
				}
				else {
					negative_vol += bars.get(i);
				}
			}
			
		}
		else {
			
			for(int i = strt-1;i >= end;i--){
				
				if(bars.get(i) > cmp) {
					ptr_i[0] = i+1;
					return ((int)(Math.abs(i-strt+1))*cmp) - negative_vol;
				}
				else{
					negative_vol += bars.get(i);
				}
			}

		}
		
		//something went wrong
		return -1;
	}
	
	private static int histogram_vol(List<Integer> bars){
		
		int max = bars.get(0);
		int max_idx = 0;
		for(int i = 1;i < bars.size();i++){
			
			int x = bars.get(i);
			if(x > max){
				max = x;
				max_idx = i;
			}
		}
		
		//the max_idx is the reference
		
		// --> max_idx
		int[] i = {0};
		int vol = 0;
		for(i[0] = 0;i[0] <= max_idx;i[0]++){
			
			int x = bars.get(i[0]);
			
			if(x != 0){
				int part_vol = calc_vol(bars,i[0],max_idx,i,true);
				if(part_vol != -1) {
					vol += part_vol;
				}
				
			}
			
			
		}
		
		
		
		// max_idx <---
		for(i[0] =  bars.size()-1;i[0] >= max_idx;i[0]--){
			
			int x = bars.get(i[0]);
			
			if(x != 0){
				int part_vol = calc_vol(bars,i[0],max_idx,i,false);
				if(part_vol != -1) {
					vol += part_vol;
				}
			}
			
		}
		
		return vol;
	}
	
}
