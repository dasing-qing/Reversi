# Reversi
##  Java黑白棋人人对战
棋盘是一个有8*8方格的棋盘。下棋时将棋下在空格中间。
开始时在棋盘正中有两白两黑四个棋子交叉放置，黑棋总是先下子。
下棋的唯一规则是只能走包围并翻转对手的棋子。
每一回合都必须至少翻转一颗对手的棋子。
当自己放下的棋子在横、竖、斜八个方向内有一个自己的棋子，则被夹在中间的对方棋子全部翻转成为自己的棋子
(夹在中间的必须全部是对手的棋子，不能有空格。例如，如果您执黑棋，并且看到在一排白棋的某一端是一颗黑棋，那么当您将一颗黑棋放在这一排的另一端时，所有的白棋都将翻转并变为黑棋)。
并且，只有在可以翻转棋子的地方才可以下子，即每一有效步都必须至少变对方一个子为自己的。 
同时，一步棋可以在数个方向上翻棋，任何被夹住的棋子都必须被翻转过来，棋手无权选择不去翻某个棋子。
如果玩家在棋盘上没有地方可以下子，则该玩家对手可以连下直到该玩家有合法棋步可下。
如果一方至少有一步合法棋步可下，他就必须落子，不得弃权。
如果棋盘填满或双方都没有棋子可以下时棋局结束。
以棋子数目来计算胜负，棋子多的一方获胜。
可以平局(在棋盘还没有下满时，如果一方的棋子已经被对方吃光，则棋局也结束。将对手棋子吃光的一方获胜)。
