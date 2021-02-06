using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace csharp
{
    public class DefaultItem : Item
    {
        public virtual void UpdateItemQuality()
        {
            if(this.Quality > 0)
            {
                this.Quality -= 1;
            }
            if(this.SellIn <= 0 && this.Quality > 0)
            {
                this.Quality -= 1;
            }
            this.SellIn -= 1;
        }
    }
}
