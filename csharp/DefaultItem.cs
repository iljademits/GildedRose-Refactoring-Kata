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
            if(this.SellIn > 0)
            {

            }
        }
    }
}
