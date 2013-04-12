        	var curPlayer = -1; // -1 is unstarted; 1 is player1's turn; 0 is palyer2's turn
        	var checkHistory = new Array(8);
			for(i=0; i < 8; i++) {
				checkHistory[i] = new Array(8);
				for(j=0;j<8;j++) {
					checkHistory[i][j] = 0;
				}
			}
        	var canvas = document.getElementById("canvas");
			var c = canvas.getContext('2d');
            var canvas_width = 600;
            var canvas_height = 600;
            var box_width = 75;
            var box_height = 75;
            var offset = -1;
            var opacity = 1.00;

            drawBoard(); // draw the checkerboard
 
            function drawBoard(){ 
                
            	var top_pos = 0;
                var left_pos = 0;
                var i;
                var alternate = false;
                
	            // draw the checkerboard in HTML5 canvas
	            do // loop for row
	            {
	            	// loop for colume
	                for( i = left_pos;i < (canvas_width - box_width + offset);i = i + (box_width + offset)) {
	                     
	                    if(alternate) {
	                        c.fillStyle = "rgba(0, 0, 0," + opacity + ")";
	                    } else {
	                        c.fillStyle = "rgba(200 , 200, 200," + opacity + ")";
	                    }
	 
	                    c.fillRect(i, top_pos, box_width, box_height);
	 
	                    // when hit the last colume, do NOT change at next row's 1st colume
	                    if(i != left_pos + (box_width + offset) * 7) {
		                    if(alternate == false) {
		                        alternate = true;
		                    } else {
		                        alternate = false;
		                    }
	                    }
	                }
	 
	                i = left_pos;
	                top_pos = top_pos + box_height + offset;
	 
	                if(top_pos > (canvas_height - box_height - 10 + offset + 50)) {
	                    break;
	                }
	                
	            } while(true); 
            }
            
            // start botton click event
            function StartGame() {
            	var name1 = document.getElementById("name1").value;
            	var name2 = document.getElementById("name2").value;
            	if ((name1 != "") && (name2 != "")) {
	            	// clean the previous actions
	            	canvas.width = canvas.width;
					// re-initiate two-deminational array[8][8]
					for(i=0; i < 8; i++) {
						checkHistory[i] = new Array(8);
						for(j=0;j<8;j++) {
							checkHistory[i][j] = 0;
						}
					}
					//checkHistory =
					//[[0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0]];
	            	// redraw the checkerboard
	            	drawBoard();
	            	
					// put the current players            	
	            	var name1 = document.getElementById("name1").value;
	            	var name2 = document.getElementById("name2").value;
	            	var player1 = document.getElementById("player1");
	            	var player2 = document.getElementById("player2");
	            	document.getElementById("player1").innerHTML = name1;
	            	document.getElementById("player2").innerHTML = name2;
	            	
	            	// show the first action is player1's turn
	            	curPlayer = 1;
	            	document.getElementById("turn").innerHTML = player1.innerHTML + "'s turn";
            	}
            }
			
			canvas.addEventListener('mousedown', ev_mousedown, false);
			
			
            function ev_mousedown(e){
				var posx=0;posy=0;
				if (e.offsetX > 0) { // IE, Chrome
					posx = e.offsetX;
					posy = e.offsetY;
				} else{ // Firefox
					posx = e.layerX;
					posy = e.layerY;
				} // this also works correctly while Zoom!=100%
				if(curPlayer != -1) {
	                var x = Math.floor(posx / box_width);
	                var y = Math.floor(posy / box_height);
	                
	                var curItemValue = checkHistory[x][y];
	                /* When clicking on the empty checker squares on the board, 
	                 * a checker should appear (something that would look like a checker), 
	                 * and remain on the board. 
	                 */
	                if(curItemValue == 0) {
	                	var gradient=c.createLinearGradient(0,0,75,0);
						c.font = 'bold 15pt Calibri';
	                	if(curPlayer == 1) {
							gradient.addColorStop("1.0","red");
							c.fillStyle=gradient;
			                c.fillText(player1.innerHTML, x*box_width, Math.floor(y*box_height + box_height/2));
			                curItemValue = 1;
		                } else {
							gradient.addColorStop("1.0","green");
							c.fillStyle=gradient;
							c.fillText(player2.innerHTML, x*box_width, Math.floor(y*box_height + box_height/2));
							curItemValue = 2;
		                }
	                } 
	                /* If you click on a square where a checker has been previously “placed”, 
	                 * that checker should disappear and be replaced with an empty board spot, 
	                 * as it was in the beginning.
	                 */
	                else {
	                	c.clearRect(x*(box_width + offset), y*(box_height + offset), box_width + offset, box_height + offset);
	                	if(Math.floor((x + y)%2) == 0) {
	                		c.fillStyle = "rgba(200 , 200, 200," + opacity + ")";
	                	} else {
	                		c.fillStyle = "rgba(0 , 0, 0," + opacity + ")";
	                	}
	                	c.fillRect(x*(box_width + offset), y*(box_height + offset), box_width + offset, box_height + offset);
	                	curItemValue = 0;
	                }
	                checkHistory[x][y] = curItemValue; // update the check history
	                //alert(checkHistory[x][y]);
	                
	                
	                // change palyer after click
	                curPlayer = Math.floor((curPlayer+1)%2);
	                // change the label alert of current player
	                if(curPlayer == 1) {
	                	document.getElementById("turn").innerHTML = player1.innerHTML + "'s turn";
	                }else{
	                	document.getElementById("turn").innerHTML = player2.innerHTML + "'s turn";
	                }
            	}
				/*
				var ev=(!e)?window.event:e;//IE,Moz
				
				if (ev.pageX){//Chrome
					posx=ev.pageX+window.pageXOffset;
					posy=ev.pageY+window.pageYOffset;
				}
				else if(ev.clientX){//IE,Chrome

						if(document.documentElement){//IE 6+ strict mode
					posx = ev.clientX + document.documentElement.scrollLeft;
					posy = ev.clientY + document.documentElement.scrollTop;
						}
						else if(document.body){//Other IE
					posx = ev.clientX + document.body.scrollLeft;
					posy = ev.clientY + document.body.scrollTop;
						}
				}
				else{return false}//old browsers
				*/
			}
            function newClick_1(event) {
            	if(curPlayer != -1) {
	            	event = event || window.event;
	                var x = Math.floor((event.pageX-canvas.offsetLeft) / box_width);
	                var y = Math.floor((event.pageY-canvas.offsetTop) / box_height);
	                
	                var curItemValue = checkHistory[x][y];
	                
	                /* When clicking on the empty checker squares on the board, 
	                 * a checker should appear (something that would look like a checker), 
	                 * and remain on the board. 
	                 */
	                if(curItemValue == 0) {
	                	var gradient=c.createLinearGradient(0,0,75,0);
						c.font = 'bold 15pt Calibri';
	                	if(curPlayer == 1) {
							gradient.addColorStop("1.0","red");
							c.fillStyle=gradient;
			                c.fillText(player1.innerHTML, x*box_width, Math.floor(y*box_height + box_height/2));
			                curItemValue = 1;
		                } else {
							gradient.addColorStop("1.0","green");
							c.fillStyle=gradient;
							c.fillText(player2.innerHTML, x*box_width, Math.floor(y*box_height + box_height/2));
							curItemValue = 2;
		                }
	                } 
	                /* If you click on a square where a checker has been previously “placed”, 
	                 * that checker should disappear and be replaced with an empty board spot, 
	                 * as it was in the beginning.
	                 */
	                else {
	                	c.clearRect(x*(box_width + offset), y*(box_height + offset), box_width + offset, box_height + offset);
	                	if(Math.floor((x + y)%2) == 0) {
	                		c.fillStyle = "rgba(200 , 200, 200," + opacity + ")";
	                	} else {
	                		c.fillStyle = "rgba(0 , 0, 0," + opacity + ")";
	                	}
	                	c.fillRect(x*(box_width + offset), y*(box_height + offset), box_width + offset, box_height + offset);
	                	curItemValue = 0;
	                }
	                checkHistory[x][y] = curItemValue; // update the check history
	                //alert(checkHistory[x][y]);
	                
	                
	                // change palyer after click
	                curPlayer = Math.floor((curPlayer+1)%2);
	                // change the label alert of current player
	                if(curPlayer == 1) {
	                	document.getElementById("turn").innerHTML = player1.innerHTML + "'s turn";
	                }else{
	                	document.getElementById("turn").innerHTML = player2.innerHTML + "'s turn";
	                }
            	}
            }
 
